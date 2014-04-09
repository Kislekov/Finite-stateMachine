// Generated from Regex.g4 by ANTLR 4.2.1

 	package main.java.antlr4;
 
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RegexParser}.
 */
public interface RegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RegexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull RegexParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull RegexParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link RegexParser#multipleExpressionStar}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExpressionStar(@NotNull RegexParser.MultipleExpressionStarContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#multipleExpressionStar}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExpressionStar(@NotNull RegexParser.MultipleExpressionStarContext ctx);

	/**
	 * Enter a parse tree produced by {@link RegexParser#symbolExpression}.
	 * @param ctx the parse tree
	 */
	void enterSymbolExpression(@NotNull RegexParser.SymbolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#symbolExpression}.
	 * @param ctx the parse tree
	 */
	void exitSymbolExpression(@NotNull RegexParser.SymbolExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link RegexParser#multipleExpressionPlus}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExpressionPlus(@NotNull RegexParser.MultipleExpressionPlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#multipleExpressionPlus}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExpressionPlus(@NotNull RegexParser.MultipleExpressionPlusContext ctx);

	/**
	 * Enter a parse tree produced by {@link RegexParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(@NotNull RegexParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(@NotNull RegexParser.ParenExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link RegexParser#multipeExpressionGeneral}.
	 * @param ctx the parse tree
	 */
	void enterMultipeExpressionGeneral(@NotNull RegexParser.MultipeExpressionGeneralContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#multipeExpressionGeneral}.
	 * @param ctx the parse tree
	 */
	void exitMultipeExpressionGeneral(@NotNull RegexParser.MultipeExpressionGeneralContext ctx);
}