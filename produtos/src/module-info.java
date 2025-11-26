/**
 * 
 */
/**
 * 
 */
module produtos {
	requires java.desktop;
	requires java.sql;

	// Export all application packages so the compiler and runtime can resolve cross-package imports
	exports Controller;
	exports Model;
	exports View;
	exports Main;
}