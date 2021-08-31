package org.caotc.code.adapter;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.caotc.code.TestEnum;
import org.caotc.code.util.EnumerableUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EnumerableAdapterTest {

    @Test
    void enumAdaptee() {
        EnumerableAdapter<TestEnum,Integer> testEnumEnumerableAdapter = EnumerableAdapter.<TestEnum,Integer>builder().adaptee(TestEnum.A).codeReader(EnumerableUtil.findCodeReaderExact(TestEnum.A)).build();
        log.debug("testEnumEnumerableAdapter:{}", testEnumEnumerableAdapter);
        Assertions.assertNotNull(testEnumEnumerableAdapter);
        Assertions.assertEquals((Integer) TestEnum.A.code(),testEnumEnumerableAdapter.code());
    }
}