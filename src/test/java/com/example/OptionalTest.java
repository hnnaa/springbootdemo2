package com.example;

import com.example.pojo.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author hnn
 * @date 2020/12/30
 */
public class OptionalTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<Object> optional = Optional.empty();
        optional.get();
    }

    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        Object a = null;
        Optional<Object> r = Optional.of(a);

    }

    @Test
    public void TestMap() throws JsonProcessingException {
        String input = "{\"a\":{\"b\":{\"c\":\"c\"}}}";
        JsonNode jsonNode = objectMapper.readTree(input);
        String c = Optional.ofNullable(jsonNode)
                .map(x -> x.path("a"))
                .map(x -> x.path("b"))
                .map(x -> x.path("c"))
                .map(JsonNode::textValue)
                .orElse("unknown");
        Assert.assertEquals("c", c);
        String c2 = Optional.ofNullable(jsonNode)
                .map(x -> x.path("a"))
                .map(x -> x.path("b"))
                .map(x -> x.path("d"))
                .map(JsonNode::textValue)
                .orElse("unknown");
        Assert.assertEquals("unknown", c2);
    }
}
