package com.nguyenndd.hotel.manager.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nguyenndd.hotel.manager.web.rest.TestUtil;

public class BiographyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Biography.class);
        Biography biography1 = new Biography();
        biography1.setId(1L);
        Biography biography2 = new Biography();
        biography2.setId(biography1.getId());
        assertThat(biography1).isEqualTo(biography2);
        biography2.setId(2L);
        assertThat(biography1).isNotEqualTo(biography2);
        biography1.setId(null);
        assertThat(biography1).isNotEqualTo(biography2);
    }
}
