package com.nguyenndd.hotel.manager.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nguyenndd.hotel.manager.web.rest.TestUtil;

public class ExtraPaymentDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtraPaymentData.class);
        ExtraPaymentData extraPaymentData1 = new ExtraPaymentData();
        extraPaymentData1.setId(1L);
        ExtraPaymentData extraPaymentData2 = new ExtraPaymentData();
        extraPaymentData2.setId(extraPaymentData1.getId());
        assertThat(extraPaymentData1).isEqualTo(extraPaymentData2);
        extraPaymentData2.setId(2L);
        assertThat(extraPaymentData1).isNotEqualTo(extraPaymentData2);
        extraPaymentData1.setId(null);
        assertThat(extraPaymentData1).isNotEqualTo(extraPaymentData2);
    }
}
