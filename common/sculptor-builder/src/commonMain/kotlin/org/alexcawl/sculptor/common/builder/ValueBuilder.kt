package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ValueContract

public abstract class ValueBuilder<VC : ValueContract>(
    protected val identifier: Identifier,
) : Builder<VC> {
    abstract override fun build(): VC
}
