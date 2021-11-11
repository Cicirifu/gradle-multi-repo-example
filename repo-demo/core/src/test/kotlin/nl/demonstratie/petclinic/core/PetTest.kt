package nl.demonstratie.petclinic.core

import com.natpryce.hamkrest.anything
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.throws
import org.junit.jupiter.api.Test
import java.util.*

class PetTest {
    @Test
    fun `Pets must have a name more than 3 characters`() {
        assertThat({ Pet(id = UUID.randomUUID(), name = "") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "a") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "ab") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "abc") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "abcd") }, anything)
    }
}
