/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel.pool;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;

/**
 * Allows to acquire and release {@link Channel} and so act as a pool of these.
 *
 * @param <C>   the {@link Channel} type to pool.
 * @param <K>   the {@link ChannelPoolKey} that is used to store and lookup the {@link Channel}s.
 */
public interface ChannelPool<C extends Channel, K extends ChannelPoolKey> {

    /**
     * Acquire a {@link Channel} for the given {@link ChannelPoolKey}. The returned {@link Future} is notified once
     * the acquire is successful and failed otherwise.
     */
    Future<C> acquire(K key);

    /**
     * Acquire a {@link Channel} for the given {@link ChannelPoolKey}. The given {@link Promise} is notified once
     * the acquire is successful and failed otherwise.
     */
    Future<C> acquire(K key, Promise<C> promise);

    /**
     * Release a {@link Channel} back to this {@link ChannelPool}. The returned {@link Future} is notified once
     * the release is successful and failed otherwise. When failed the {@link Channel} will automatically closed.
     */
    Future<Boolean> release(C channel);

    /**
     * Release a {@link Channel} back to this {@link ChannelPool}.  The given {@link Promise} is notified once
     * the release is successful and failed otherwise. When failed the {@link Channel} will automatically closed.
     */
    Future<Boolean> release(C channel, Promise<Boolean> promise);
}
