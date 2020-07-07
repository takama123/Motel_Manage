package com.nguyenndd.hotel.manager.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nguyenndd.hotel.manager.web.rest.TestUtil;

public class BillDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillDetails.class);
        BillDetails billDetails1 = new BillDetails();
        billDetails1.setId(1L);
        BillDetails billDetails2 = new BillDetails();
        billDetails2.setId(billDetails1.getId());
        assertThat(billDetails1).isEqualTo(billDetails2);
        billDetails2.setId(2L);
        assertThat(billDetails1).isNotEqualTo(billDetails2);
        billDetails1.setId(null);
        assertThat(billDetails1).isNotEqualTo(billDetails2);
    }
}
