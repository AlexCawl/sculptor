package org.alexcawl.sculptor.common.builder.scopes

import org.alexcawl.sculptor.common.builder.SculptorDsl
import org.alexcawl.sculptor.common.contract.ValueContract

@SculptorDsl
public interface ValuePlacer {
    public fun value(value: ValueContract)

    public fun <VC : ValueContract> value(builder: () -> VC)
}
