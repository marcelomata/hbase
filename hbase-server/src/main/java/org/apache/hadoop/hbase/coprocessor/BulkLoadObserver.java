/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase.coprocessor;

import java.io.IOException;

import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.HBaseInterfaceAudience;
import org.apache.hadoop.hbase.classification.InterfaceAudience;
import org.apache.hadoop.hbase.classification.InterfaceStability;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.PrepareBulkLoadRequest;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.CleanupBulkLoadRequest;

/**
 * Coprocessors implement this interface to observe and mediate bulk load operations.
 */
@InterfaceAudience.LimitedPrivate(HBaseInterfaceAudience.COPROC)
@InterfaceStability.Evolving
public interface BulkLoadObserver extends Coprocessor {
    /**
      * Called as part of SecureBulkLoadEndpoint.prepareBulkLoad() RPC call.
      * It can't bypass the default action, e.g., ctx.bypass() won't have effect.
      * @param ctx the environment to interact with the framework and master
      * @throws IOException
      */
    void prePrepareBulkLoad(ObserverContext<RegionCoprocessorEnvironment> ctx,
                            PrepareBulkLoadRequest request) throws IOException;

    /**
      * Called as part of SecureBulkLoadEndpoint.cleanupBulkLoad() RPC call.
      * It can't bypass the default action, e.g., ctx.bypass() won't have effect.
      * @param ctx the environment to interact with the framework and master
      * @throws IOException
      */
    void preCleanupBulkLoad(ObserverContext<RegionCoprocessorEnvironment> ctx,
                            CleanupBulkLoadRequest request) throws IOException;
}
