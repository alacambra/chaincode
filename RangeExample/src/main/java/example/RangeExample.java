/*
Copyright DTCC 2016 All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;

import javax.json.Json;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by cadmin on 6/30/16.
 */
public class RangeExample extends ChaincodeBase {
    private static Log logger = LogFactory.getLog(RangeExample.class);

    @java.lang.Override
    public String run(ChaincodeStub stub, String function, String[] args) {
        logger.info("In run, function:" + function);
        switch (function) {
            case "put":
                for (int i = 0; i < args.length; i += 2) {
                    logger.info("[run] saving states " + args[i] + ", " + args[i + 1]);
                    stub.putState(args[i], args[i + 1]);
                }
                break;
            case "del":
                for (String arg : args)
                    stub.delState(arg);
                break;
            default:
                logger.error("No matching case for function:" + function);
                return Json.createObjectBuilder().add("error", "No matching case for function:" + function).build().toString();

        }
        return null;
    }


    @java.lang.Override
    public String query(ChaincodeStub stub, String function, String[] args) {
        logger.info("[query] received " + function + ":" + Arrays.asList(args));
        switch (function) {
            case "get": {
                String key = args[0];
                String result = stub.getState(args[0]);
                logger.info("[query] received from stub" + result);

                return Json.createObjectBuilder().add(key, result).build().toString();
            }
            case "keys": {
                Map<String, String> keysIter = null;
                if (args.length >= 2) {
                    keysIter = stub.rangeQueryState(args[0], args[1]);
                } else {
                    keysIter = stub.rangeQueryState("", "");
                }

                return keysIter.entrySet().toString();
            }
            default:
                logger.error("No matching case for function:" + function);
                return Json.createObjectBuilder().add("error", "No matching case for function:" + function).build().toString();
        }

    }

    @java.lang.Override
    public String getChaincodeID() {
        return "RangeExample";
    }

    public static void main(String[] args) throws Exception {
        logger.info("starting");
        new RangeExample().start(args);
    }

}
