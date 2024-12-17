package io.micronaut.http.netty.body

import io.netty.buffer.Unpooled
import io.netty.channel.embedded.EmbeddedChannel
import reactor.core.publisher.Flux
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class StreamingNettyByteBodySpec extends Specification {
    def move() {
        given:
        def a = NettyBodyAdapter.adapt(Flux.just(Unpooled.copiedBuffer("foo", StandardCharsets.UTF_8)), new EmbeddedChannel().eventLoop())
        def b = a.move()

        when:
        a.close()
        then:
        b.buffer().get().toString(StandardCharsets.UTF_8) == "foo"
    }
}
