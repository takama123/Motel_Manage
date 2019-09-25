package com.nguyenndd.hotel.manager.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nguyenndd.hotel.manager.web.rest.TestUtil;

public class MotelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Motel.class);
        Motel motel1 = new Motel();
        motel1.setId(1L);
        Motel motel2 = new Motel();
        motel2.setId(motel1.getId());
        assertThat(motel1).isEqualTo(motel2);
        motel2.setId(2L);
        assertThat(motel1).isNotEqualTo(motel2);
        motel1.setId(null);
        assertThat(motel1).isNotEqualTo(motel2);
    }
}
