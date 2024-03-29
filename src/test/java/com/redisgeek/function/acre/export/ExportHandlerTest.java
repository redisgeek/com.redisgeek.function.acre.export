package com.redisgeek.function.acre.export;

import com.microsoft.azure.functions.ExecutionContext;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class ExportHandlerTest {

    public void test() {
        Mono<String> result = new Export().apply(Mono.just(Optional.empty()));
        assertThat(result.block()).isNotEmpty();
    }

    public void start() {
        FunctionInvoker<String, String> handler = new FunctionInvoker<>(Export.class);
        String result = handler.handleRequest("", new ExecutionContext() {
            @Override
            public Logger getLogger() {
                return Logger.getLogger(ExportHandlerTest.class.getName());
            }

            @Override
            public String getInvocationId() {
                return "id1";
            }

            @Override
            public String getFunctionName() {
                return "Export";
            }
        });
        handler.close();
        assertThat(result).isNotEmpty();
    }
}
