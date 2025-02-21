package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.BooleanValueContract
import org.alexcawl.sculptor.common.contract.DoubleValueContract
import org.alexcawl.sculptor.common.contract.FloatValueContract
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.IntValueContract
import org.alexcawl.sculptor.common.contract.LongValueContract
import org.alexcawl.sculptor.common.contract.NumberValueContract
import org.alexcawl.sculptor.common.contract.StringValueContract
import org.alexcawl.sculptor.common.contract.ValueContract

@SculptorBuilder
public abstract class ValueBuilder<VC : ValueContract>(
    protected val identifier: Identifier,
) : Builder<VC> {
    abstract override fun build(): VC
}

@SculptorBuilder
public class StringValueBuilder(
    identifier: Identifier,
    private val value: String,
) : ValueBuilder<ValueContract>(identifier) {
    override fun build(): ValueContract = StringValueContract(identifier, value)
}

@SculptorBuilder
public class BooleanValueBuilder(
    identifier: Identifier,
    private val value: Boolean,
) : ValueBuilder<ValueContract>(identifier) {
    override fun build(): ValueContract = BooleanValueContract(identifier, value)
}

@SculptorBuilder
public abstract class NumberValueBuilder<N : Number, NVC : NumberValueContract>(
    identifier: Identifier,
    protected val value: N,
) : ValueBuilder<NumberValueContract>(identifier) {
    abstract override fun build(): NVC
}

@SculptorBuilder
public class FloatValueBuilder(
    identifier: Identifier,
    value: Float,
) : NumberValueBuilder<Float, FloatValueContract>(identifier, value) {
    override fun build(): FloatValueContract = FloatValueContract(identifier, value)
}

@SculptorBuilder
public class DoubleValueBuilder(
    identifier: Identifier,
    value: Double,
) : NumberValueBuilder<Double, DoubleValueContract>(identifier, value) {
    override fun build(): DoubleValueContract = DoubleValueContract(identifier, value)
}

@SculptorBuilder
public class IntValueBuilder(
    identifier: Identifier,
    value: Int,
) : NumberValueBuilder<Int, IntValueContract>(identifier, value) {
    override fun build(): IntValueContract = IntValueContract(identifier, value)
}

@SculptorBuilder
public class LongValueBuilder(
    identifier: Identifier,
    value: Long,
) : NumberValueBuilder<Long, LongValueContract>(identifier, value) {
    override fun build(): LongValueContract = LongValueContract(identifier, value)
}
