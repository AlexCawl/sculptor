package org.alexcawl.sculptor.core.contract

/**
 * Represents a contract for modifiers.
 * This interface is designed to be extended by specific modifier implementations.
 * The companion object provides a default implementation that delegates to an empty list,
 * which can be useful for cases where no modifiers are needed.
 */
public interface ModifierContract {
    /**
     * Companion object that implements the [ModifierContract] interface.
     * It delegates to an empty list, effectively providing a builder implementation.
     */
    public companion object : List<ModifierContract> by emptyList()
}
