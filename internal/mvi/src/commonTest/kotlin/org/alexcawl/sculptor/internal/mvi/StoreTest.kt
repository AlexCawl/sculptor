package org.alexcawl.sculptor.internal.mvi

import org.junit.Test
import kotlin.reflect.KClass
import kotlin.test.assertFalse

class StoreTest {
    interface A
    
    open class B : A
    
    class C : B()
    
    class D
    
    @Test
    fun test() {
        val kclass: KClass<A> = A::class
        assert(kclass.isInstance(B()))
        assert(kclass.isInstance(C()))
        assertFalse(kclass.isInstance(D()))
    }
}