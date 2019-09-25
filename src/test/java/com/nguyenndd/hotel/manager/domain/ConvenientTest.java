package com.nguyenndd.hotel.manager.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nguyenndd.hotel.manager.web.rest.TestUtil;

public class ConvenientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Convenient.class);
        Convenient convenient1 = new Convenient();
        convenient1.setId(1L);
        Convenient convenient2 = new Convenient();
        convenient2.setId(convenient1.getId());
        assertThat(convenient1).isEqualTo(convenient2);
        convenient2.setId(2L);
        assertThat(convenient1).isNotEqualTo(convenient2);
        convenient1.setId(null);
        assertThat(convenient1).isNotEqualTo(convenient2);
    }
}
