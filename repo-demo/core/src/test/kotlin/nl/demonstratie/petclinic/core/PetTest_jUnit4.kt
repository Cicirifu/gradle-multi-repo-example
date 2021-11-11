package nl.demonstratie.petclinic.core

import com.natpryce.hamkrest.anything
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.throws
import org.junit.Test
import java.util.*

class PetTestjUnit4 {
    // Specifically using jUnit 4 annotations here
    @Test
    fun `JUNIT4 - Pets must have a name more than 3 characters`() {
        assertThat({ Pet(id = UUID.randomUUID(), name = "") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "a") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "ab") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "abc") }, throws<IllegalArgumentException>())
        assertThat({ Pet(id = UUID.randomUUID(), name = "abcd") }, anything)
    }
}
