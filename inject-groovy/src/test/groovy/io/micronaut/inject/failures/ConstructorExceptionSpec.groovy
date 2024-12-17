/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.inject.failures

import io.micronaut.context.ApplicationContext
import io.micronaut.context.exceptions.BeanInstantiationException
import jakarta.inject.Inject
import jakarta.inject.Singleton
import spock.lang.Specification
/**
 * Created by graemerocher on 17/05/2017.
 */
class ConstructorExceptionSpec extends Specification {

    void "test error message when exception occurs in constructor"() {
        given:
        ApplicationContext context = ApplicationContext.run()

        when:"A bean is obtained that has a setter with @Inject"
        MyClassB b =  context.getBean(MyClassB)

        then:"The implementation is injected"
        def e = thrown(BeanInstantiationException)
        //e.cause.message == 'bad'
        e.message.normalize() == '''\
Error instantiating bean of type  [io.micronaut.inject.failures.ConstructorExceptionSpec$MyClassA]

Message: bad
Path Taken:
new i.m.i.f.C$MyClassB()
\\---> i.m.i.f.C$MyClassB#propA
      \\---> new i.m.i.f.C$MyClassA([MyClassC propC])'''

        cleanup:
        context.close()
    }

    @Singleton
    static class MyClassC {
        MyClassC() {
            throw new RuntimeException("bad")
        }
    }
    @Singleton
    static class MyClassA {
        MyClassA(MyClassC propC) {

        }
    }

    static class MyClassB {
        @Inject
        private MyClassA propA

        MyClassA getA() {
            return this.propA
        }
    }

}
