// Generated from Regex.g4 by ANTLR 4.2.1

 	package main.java.antlr4;
 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, RPAREN=2, LPAREN=3, PLUS=4, STAR=5, SYMBOL=6;
	public static final String[] tokenNames = {
		"<INVALID>", "'|'", "')'", "'('", "'+'", "'*'", "SYMBOL"
	};
	public static final int
		RULE_expression = 0, RULE_parenExpression = 1, RULE_multipleExpressionStar = 2, 
		RULE_multipleExpressionPlus = 3, RULE_multipeExpressionGeneral = 4, RULE_symbolExpression = 5;
	public static final String[] ruleNames = {
		"expression", "parenExpression", "multipleExpressionStar", "multipleExpressionPlus", 
		"multipeExpressionGeneral", "symbolExpression"
	};

	@Override
	public String getGrammarFileName() { return "Regex.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RegexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public SymbolExpressionContext symbolExpression(int i) {
			return getRuleContext(SymbolExpressionContext.class,i);
		}
		public List<MultipleExpressionPlusContext> multipleExpressionPlus() {
			return getRuleContexts(MultipleExpressionPlusContext.class);
		}
		public List<ParenExpressionContext> parenExpression() {
			return getRuleContexts(ParenExpressionContext.class);
		}
		public TerminalNode AND() { return getToken(RegexParser.AND, 0); }
		public List<SymbolExpressionContext> symbolExpression() {
			return getRuleContexts(SymbolExpressionContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MultipleExpressionStarContext multipleExpressionStar(int i) {
			return getRuleContext(MultipleExpressionStarContext.class,i);
		}
		public List<MultipleExpressionStarContext> multipleExpressionStar() {
			return getRuleContexts(MultipleExpressionStarContext.class);
		}
		public ParenExpressionContext parenExpression(int i) {
			return getRuleContext(ParenExpressionContext.class,i);
		}
		public MultipleExpressionPlusContext multipleExpressionPlus(int i) {
			return getRuleContext(MultipleExpressionPlusContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN || _la==SYMBOL) {
				{
				setState(16);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(12); parenExpression();
					}
					break;

				case 2:
					{
					setState(13); multipleExpressionStar();
					}
					break;

				case 3:
					{
					setState(14); multipleExpressionPlus();
					}
					break;

				case 4:
					{
					setState(15); symbolExpression();
					}
					break;
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(21); match(AND);
				setState(22); expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenExpressionContext extends ParserRuleContext {
		public TerminalNode RPAREN() { return getToken(RegexParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(RegexParser.LPAREN, 0); }
		public ParenExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitParenExpression(this);
		}
	}

	public final ParenExpressionContext parenExpression() throws RecognitionException {
		ParenExpressionContext _localctx = new ParenExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parenExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); match(LPAREN);
			setState(26); expression();
			setState(27); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleExpressionStarContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(RegexParser.STAR, 0); }
		public MultipeExpressionGeneralContext multipeExpressionGeneral() {
			return getRuleContext(MultipeExpressionGeneralContext.class,0);
		}
		public MultipleExpressionStarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleExpressionStar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterMultipleExpressionStar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitMultipleExpressionStar(this);
		}
	}

	public final MultipleExpressionStarContext multipleExpressionStar() throws RecognitionException {
		MultipleExpressionStarContext _localctx = new MultipleExpressionStarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_multipleExpressionStar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); multipeExpressionGeneral();
			setState(30); match(STAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleExpressionPlusContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(RegexParser.PLUS, 0); }
		public MultipeExpressionGeneralContext multipeExpressionGeneral() {
			return getRuleContext(MultipeExpressionGeneralContext.class,0);
		}
		public MultipleExpressionPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleExpressionPlus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterMultipleExpressionPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitMultipleExpressionPlus(this);
		}
	}

	public final MultipleExpressionPlusContext multipleExpressionPlus() throws RecognitionException {
		MultipleExpressionPlusContext _localctx = new MultipleExpressionPlusContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multipleExpressionPlus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); multipeExpressionGeneral();
			setState(33); match(PLUS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipeExpressionGeneralContext extends ParserRuleContext {
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public SymbolExpressionContext symbolExpression() {
			return getRuleContext(SymbolExpressionContext.class,0);
		}
		public MultipeExpressionGeneralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipeExpressionGeneral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterMultipeExpressionGeneral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitMultipeExpressionGeneral(this);
		}
	}

	public final MultipeExpressionGeneralContext multipeExpressionGeneral() throws RecognitionException {
		MultipeExpressionGeneralContext _localctx = new MultipeExpressionGeneralContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_multipeExpressionGeneral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(35); parenExpression();
				}
				break;
			case SYMBOL:
				{
				setState(36); symbolExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolExpressionContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(RegexParser.SYMBOL, 0); }
		public SymbolExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterSymbolExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitSymbolExpression(this);
		}
	}

	public final SymbolExpressionContext symbolExpression() throws RecognitionException {
		SymbolExpressionContext _localctx = new SymbolExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_symbolExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); match(SYMBOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b,\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\7\2\23\n\2\f\2\16\2"+
		"\26\13\2\3\2\3\2\5\2\32\n\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\5\6(\n\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2+\2\24\3\2\2\2\4\33\3"+
		"\2\2\2\6\37\3\2\2\2\b\"\3\2\2\2\n\'\3\2\2\2\f)\3\2\2\2\16\23\5\4\3\2\17"+
		"\23\5\6\4\2\20\23\5\b\5\2\21\23\5\f\7\2\22\16\3\2\2\2\22\17\3\2\2\2\22"+
		"\20\3\2\2\2\22\21\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25"+
		"\31\3\2\2\2\26\24\3\2\2\2\27\30\7\3\2\2\30\32\5\2\2\2\31\27\3\2\2\2\31"+
		"\32\3\2\2\2\32\3\3\2\2\2\33\34\7\5\2\2\34\35\5\2\2\2\35\36\7\4\2\2\36"+
		"\5\3\2\2\2\37 \5\n\6\2 !\7\7\2\2!\7\3\2\2\2\"#\5\n\6\2#$\7\6\2\2$\t\3"+
		"\2\2\2%(\5\4\3\2&(\5\f\7\2\'%\3\2\2\2\'&\3\2\2\2(\13\3\2\2\2)*\7\b\2\2"+
		"*\r\3\2\2\2\6\22\24\31\'";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}