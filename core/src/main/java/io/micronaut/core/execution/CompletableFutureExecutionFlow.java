/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.core.execution;

import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.NonNull;

import java.util.concurrent.CompletionStage;

/**
 * The completable future execution flow.
 *
 * @param <T> The value type
 * @author Denis Stepnov
 * @since 4.0.0
 */
@Internal
public interface CompletableFutureExecutionFlow<T> extends ExecutionFlow<T> {

    /**
     * Create a completable future flow representing a value.
     *
     * @param value The value
     * @param <K>   The value type
     * @return a new flow
     */
    @NonNull
    static <K> ExecutionFlow<K> just(@NonNull CompletionStage<K> value) {
        return (ExecutionFlow<K>) new CompletableFutureExecutionFlowImpl((CompletionStage<Object>) value);
    }

}
