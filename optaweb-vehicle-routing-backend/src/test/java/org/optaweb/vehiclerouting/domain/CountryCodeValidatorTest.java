/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.vehiclerouting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.optaweb.vehiclerouting.domain.CountryCodeValidator.validate;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class CountryCodeValidatorTest {

    @Test
    void should_fail_on_invalid_country_codes() {
        assertThatNullPointerException().isThrownBy(() -> validate(null));
        assertThatNullPointerException().isThrownBy(() -> validate(Arrays.asList("US", null, "CA")));
        assertThatIllegalArgumentException().isThrownBy(() -> validate(Arrays.asList("XX")));
        assertThatIllegalArgumentException().isThrownBy(() -> validate(Arrays.asList("CZE")));
        assertThatIllegalArgumentException().isThrownBy(() -> validate(Arrays.asList("D")));
        assertThatIllegalArgumentException().isThrownBy(() -> validate(Arrays.asList("")));
        assertThatIllegalArgumentException().isThrownBy(() -> validate(Arrays.asList("US", "XY", "CA")));
    }

    @Test
    void should_ignore_case_and_convert_to_upper_case() {
        assertThat(validate(Arrays.asList("us"))).containsExactly("US");
    }

    @Test
    void should_allow_multiple_values() {
        assertThat(validate(Arrays.asList("US", "ca"))).containsExactly("US", "CA");
    }

    @Test
    void should_allow_empty_list() {
        assertThat(validate(new ArrayList<>())).isEmpty();
    }
}
