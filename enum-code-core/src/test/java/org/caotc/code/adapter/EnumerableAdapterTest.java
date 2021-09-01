package org.caotc.code.adapter;

import lombok.extern.slf4j.Slf4j;
import org.caotc.code.model.CodeFieldEnum;
import org.caotc.code.util.EnumerableUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class EnumerableAdapterTest {

    @Test
    void enumAdaptee() {
        EnumerableAdapter<CodeFieldEnum,Integer> testEnumEnumerableAdapter = EnumerableAdapter.<CodeFieldEnum,Integer>builder().adaptee(CodeFieldEnum.A).codeReader(EnumerableUtil.findCodeReaderExact(CodeFieldEnum.A)).build();
        log.debug("testEnumEnumerableAdapter:{}", testEnumEnumerableAdapter);
        Assertions.assertNotNull(testEnumEnumerableAdapter);
        Assertions.assertEquals((Integer) CodeFieldEnum.A.code(),testEnumEnumerableAdapter.code());
    }


}