package org.alexcawl.sculptor.core.contractor

public interface Bundle {
    public val contractBundle: ContractBundle

    public val presenterBundle: PresenterBundle

    public val rendererBundle: RendererBundle

    public interface Consumer {
        public fun bundle(bundle: Bundle)
    }
}
