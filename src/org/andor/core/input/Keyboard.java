/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.lwjgl.glfw.GLFW;

public class Keyboard {
	
	/* The last keyboard event */
	/**
	 * The last keyboard event that has occurred
	 */
	public static KeyboardEvent lastKeyboardEvent;

	/* ALL OF THE KEYS ON THE KEYBOARD (CODES) */

	/* The numbers on the keyboard */
	public static int KEY_0_CODE = 1;
	public static int KEY_1_CODE = 2;
	public static int KEY_2_CODE = 3;
	public static int KEY_3_CODE = 4;
	public static int KEY_4_CODE = 5;
	public static int KEY_5_CODE = 6;
	public static int KEY_6_CODE = 7;
	public static int KEY_7_CODE = 8;
	public static int KEY_8_CODE = 9;
	public static int KEY_9_CODE = 10;

	/* The letters on the keyboard */
	public static int KEY_A_CODE = 11;
	public static int KEY_B_CODE = 12;
	public static int KEY_C_CODE = 13;
	public static int KEY_D_CODE = 14;
	public static int KEY_E_CODE = 15;
	public static int KEY_F_CODE = 16;
	public static int KEY_G_CODE = 17;
	public static int KEY_H_CODE = 18;
	public static int KEY_I_CODE = 19;
	public static int KEY_J_CODE = 20;
	public static int KEY_K_CODE = 21;
	public static int KEY_L_CODE = 22;
	public static int KEY_M_CODE = 23;
	public static int KEY_N_CODE = 24;
	public static int KEY_O_CODE = 25;
	public static int KEY_P_CODE = 26;
	public static int KEY_Q_CODE = 27;
	public static int KEY_R_CODE = 28;
	public static int KEY_S_CODE = 29;
	public static int KEY_T_CODE = 30;
	public static int KEY_U_CODE = 31;
	public static int KEY_V_CODE = 32;
	public static int KEY_W_CODE = 33;
	public static int KEY_X_CODE = 34;
	public static int KEY_Y_CODE = 35;
	public static int KEY_Z_CODE = 36;

	/* The 'F' keys on the keyboard */
	public static int KEY_F1_CODE = 37;
	public static int KEY_F2_CODE = 38;
	public static int KEY_F3_CODE = 39;
	public static int KEY_F4_CODE = 40;
	public static int KEY_F5_CODE = 41;
	public static int KEY_F6_CODE = 42;
	public static int KEY_F7_CODE = 43;
	public static int KEY_F8_CODE = 44;
	public static int KEY_F9_CODE = 45;
	public static int KEY_F10_CODE = 46;
	public static int KEY_F11_CODE = 47;
	public static int KEY_F12_CODE = 48;

	/* The number pad keys on the keyboard */
	public static int KEY_NUMLOCK_CODE = 49;
	public static int KEY_NUMPAD0_CODE = 50;
	public static int KEY_NUMPAD1_CODE = 51;
	public static int KEY_NUMPAD2_CODE = 52;
	public static int KEY_NUMPAD3_CODE = 53;
	public static int KEY_NUMPAD4_CODE = 54;
	public static int KEY_NUMPAD5_CODE = 55;
	public static int KEY_NUMPAD6_CODE = 56;
	public static int KEY_NUMPAD7_CODE = 57;
	public static int KEY_NUMPAD8_CODE = 58;
	public static int KEY_NUMPAD9_CODE = 59;
	public static int KEY_NUMPADCOMMA_CODE = 60;
	public static int KEY_NUMPADENTER_CODE = 61;
	public static int KEY_NUMPADEQUALS_CODE = 62;

	/* The miscellaneous keys on the keyboard */
	public static int KEY_ADD_CODE = 63;
	public static int KEY_APOSTROPHE_CODE = 64;
	public static int KEY_APPS_CODE = 65;
	public static int KEY_AT_CODE = 66;
	public static int KEY_AX_CODE = 67;
	public static int KEY_BACKSPACE_CODE = 68;
	public static int KEY_BACKSLASH_CODE = 69;
	public static int KEY_CAPSLOCK_CODE = 70;
	public static int KEY_CIRCUMFLEX_CODE = 71;
	public static int KEY_CLEAR_CODE = 72;
	public static int KEY_COLON_CODE = 73;
	public static int KEY_COMMA_CODE = 74;
	public static int KEY_CONVERT_CODE = 75;
	public static int KEY_PERIOD_CODE = 76;
	public static int KEY_DELETE_CODE = 77;
	public static int KEY_DIVIDE_CODE = 78;
	public static int KEY_END_CODE = 79;
	public static int KEY_EQUALS_CODE = 80;
	public static int KEY_ESCAPE_CODE = 81;
	public static int KEY_FUNCTION_CODE = 82;
	public static int KEY_GRAVE_CODE = 83;
	public static int KEY_HOME_CODE = 84;
	public static int KEY_INSERT_CODE = 85;
	public static int KEY_KANA_CODE = 86;
	public static int KEY_KANJI_CODE = 87;
	public static int KEY_LBRACKET_CODE = 88;
	public static int KEY_RBRACKET_CODE = 89;
	public static int KEY_LCONTROL_CODE = 90;
	public static int KEY_RCONTROL_CODE = 91;
	public static int KEY_LALT_CODE = 92;
	public static int KEY_RALT_CODE = 93;
	public static int KEY_LMETA_CODE = 94;
	public static int KEY_RMETA_CODE = 95;
	public static int KEY_MINUS_CODE = 96;
	public static int KEY_MULTIPLY_CODE = 97;
	public static int KEY_PAGE_DOWN_CODE = 98;
	public static int KEY_NOCONVERT_CODE = 99;
	public static int KEY_PAUSE_CODE = 100;
	public static int KEY_POWER_CODE = 102;
	public static int KEY_PAGE_UP_CODE = 103;
	public static int KEY_RETURN_CODE = 104;
	public static int KEY_SCROLLLOCK_CODE = 105;
	public static int KEY_SECTION_CODE = 106;
	public static int KEY_SEMICOLON_CODE = 107;
	public static int KEY_FORWARD_SLASH_CODE = 108;
	public static int KEY_BACK_SLASH_CODE = 109;
	public static int KEY_SLEEP_CODE = 110;
	public static int KEY_SPACE_CODE = 111;
	public static int KEY_STOP_CODE = 112;
	public static int KEY_SUBTRACT_CODE = 113;
	public static int KEY_SYSRQ_CODE = 114;
	public static int KEY_TAB_CODE = 115;
	public static int KEY_UNDERSCORE_CODE = 116;
	public static int KEY_UNKNOWN_CODE = 117;
	public static int KEY_YEN_CODE = 118;
	public static int KEY_UP_CODE = 119;
	public static int KEY_DOWN_CODE = 120;
	public static int KEY_LEFT_CODE = 121;
	public static int KEY_RIGHT_CODE = 122;
	public static int KEY_LSHIFT_CODE = 123;
	public static int KEY_RSHIFT_CODE = 124;

	/* ALL OF THE KEYS ON THE KEYBOARD */

	/* The numbers on the keyboard */
	public static boolean KEY_0 = false;
	public static boolean KEY_1 = false;
	public static boolean KEY_2 = false;
	public static boolean KEY_3 = false;
	public static boolean KEY_4 = false;
	public static boolean KEY_5 = false;
	public static boolean KEY_6 = false;
	public static boolean KEY_7 = false;
	public static boolean KEY_8 = false;
	public static boolean KEY_9 = false;

	/* The letters on the keyboard */
	public static boolean KEY_A = false;
	public static boolean KEY_B = false;
	public static boolean KEY_C = false;
	public static boolean KEY_D = false;
	public static boolean KEY_E = false;
	public static boolean KEY_F = false;
	public static boolean KEY_G = false;
	public static boolean KEY_H = false;
	public static boolean KEY_I = false;
	public static boolean KEY_J = false;
	public static boolean KEY_K = false;
	public static boolean KEY_L = false;
	public static boolean KEY_M = false;
	public static boolean KEY_N = false;
	public static boolean KEY_O = false;
	public static boolean KEY_P = false;
	public static boolean KEY_Q = false;
	public static boolean KEY_R = false;
	public static boolean KEY_S = false;
	public static boolean KEY_T = false;
	public static boolean KEY_U = false;
	public static boolean KEY_V = false;
	public static boolean KEY_W = false;
	public static boolean KEY_X = false;
	public static boolean KEY_Y = false;
	public static boolean KEY_Z = false;

	/* The 'F' keys on the keyboard */
	public static boolean KEY_F1 = false;
	public static boolean KEY_F2 = false;
	public static boolean KEY_F3 = false;
	public static boolean KEY_F4 = false;
	public static boolean KEY_F5 = false;
	public static boolean KEY_F6 = false;
	public static boolean KEY_F7 = false;
	public static boolean KEY_F8 = false;
	public static boolean KEY_F9 = false;
	public static boolean KEY_F10 = false;
	public static boolean KEY_F11 = false;
	public static boolean KEY_F12 = false;

	/* The number pad keys on the keyboard */
	public static boolean KEY_NUMLOCK = false;
	public static boolean KEY_NUMPAD0 = false;
	public static boolean KEY_NUMPAD1 = false;
	public static boolean KEY_NUMPAD2 = false;
	public static boolean KEY_NUMPAD3 = false;
	public static boolean KEY_NUMPAD4 = false;
	public static boolean KEY_NUMPAD5 = false;
	public static boolean KEY_NUMPAD6 = false;
	public static boolean KEY_NUMPAD7 = false;
	public static boolean KEY_NUMPAD8 = false;
	public static boolean KEY_NUMPAD9 = false;
	public static boolean KEY_NUMPADCOMMA = false;
	public static boolean KEY_NUMPADENTER = false;
	public static boolean KEY_NUMPADEQUALS = false;

	/* The miscellaneous keys on the keyboard */
	public static boolean KEY_ADD = false;
	public static boolean KEY_APOSTROPHE = false;
	public static boolean KEY_APPS = false;
	public static boolean KEY_AT = false;
	public static boolean KEY_AX = false;
	public static boolean KEY_BACKSPACE = false;
	public static boolean KEY_CAPSLOCK = false;
	public static boolean KEY_CLEAR = false;
	public static boolean KEY_COLON = false;
	public static boolean KEY_COMMA = false;
	public static boolean KEY_DELETE = false;
	public static boolean KEY_DIVIDE = false;
	public static boolean KEY_END = false;
	public static boolean KEY_EQUALS = false;
	public static boolean KEY_ESCAPE = false;
	public static boolean KEY_FUNCTION = false;
	public static boolean KEY_GRAVE = false;
	public static boolean KEY_HOME = false;
	public static boolean KEY_INSERT = false;
	public static boolean KEY_KANA = false;
	public static boolean KEY_KANJI = false;
	public static boolean KEY_LBRACKET = false;
	public static boolean KEY_RBRACKET = false;
	public static boolean KEY_LCONTROL = false;
	public static boolean KEY_RCONTROL = false;
	public static boolean KEY_LALT = false;
	public static boolean KEY_RALT = false;
	public static boolean KEY_LMETA = false;
	public static boolean KEY_RMETA = false;
	public static boolean KEY_MINUS = false;
	public static boolean KEY_MULTIPLY = false;
	public static boolean KEY_PAGE_DOWN = false;
	public static boolean KEY_NOCONVERT = false;;
	public static boolean KEY_PAUSE = false;
	public static boolean KEY_PERIOD = false;
	public static boolean KEY_POWER = false;
	public static boolean KEY_PAGE_UP = false;
	public static boolean KEY_RETURN = false;
	public static boolean KEY_SCROLLLOCK = false;
	public static boolean KEY_SECTION = false;
	public static boolean KEY_SEMICOLON = false;
	public static boolean KEY_FORWARD_SLASH = false;
	public static boolean KEY_BACK_SLASH = false;
	public static boolean KEY_SLEEP = false;
	public static boolean KEY_SPACE	 = false;
	public static boolean KEY_STOP = false;
	public static boolean KEY_SUBTRACT = false;
	public static boolean KEY_SYSRQ = false;
	public static boolean KEY_TAB = false;
	public static boolean KEY_UNDERSCORE = false;
	public static boolean KEY_UNKNOWN = false;
	public static boolean KEY_UP = false;
	public static boolean KEY_DOWN = false;
	public static boolean KEY_LEFT = false;
	public static boolean KEY_RIGHT = false;
	public static boolean KEY_LSHIFT = false;
	public static boolean KEY_RSHIFT = false;

	/* The static method that returns whether a key is pressed based
	 * on its key code */
	public static boolean isKeyDown(int keyCode) {
		//Check the key code then return the boolean value
		if (keyCode == KEY_0_CODE)
			return KEY_0;
		else if (keyCode == KEY_1_CODE)
			return KEY_1;
		else if (keyCode == KEY_2_CODE)
			return KEY_2;
		else if (keyCode == KEY_3_CODE)
			return KEY_3;
		else if (keyCode == KEY_4_CODE)
			return KEY_4;
		else if (keyCode == KEY_5_CODE)
			return KEY_5;
		else if (keyCode == KEY_6_CODE)
			return KEY_6;
		else if (keyCode == KEY_7_CODE)
			return KEY_7;
		else if (keyCode == KEY_8_CODE)
			return KEY_8;
		else if (keyCode == KEY_9_CODE)
			return KEY_9;
		else if (keyCode == KEY_A_CODE)
			return KEY_A;
		else if (keyCode == KEY_B_CODE)
			return KEY_B;
		else if (keyCode == KEY_C_CODE)
			return KEY_C;
		else if (keyCode == KEY_D_CODE)
			return KEY_D;
		else if (keyCode == KEY_E_CODE)
			return KEY_E;
		else if (keyCode == KEY_F_CODE)
			return KEY_F;
		else if (keyCode == KEY_G_CODE)
			return KEY_G;
		else if (keyCode == KEY_H_CODE)
			return KEY_H;
		else if (keyCode == KEY_I_CODE)
			return KEY_I;
		else if (keyCode == KEY_J_CODE)
			return KEY_J;
		else if (keyCode == KEY_K_CODE)
			return KEY_K;
		else if (keyCode == KEY_L_CODE)
			return KEY_L;
		else if (keyCode == KEY_M_CODE)
			return KEY_M;
		else if (keyCode == KEY_N_CODE)
			return KEY_N;
		else if (keyCode == KEY_O_CODE)
			return KEY_O;
		else if (keyCode == KEY_P_CODE)
			return KEY_P;
		else if (keyCode == KEY_Q_CODE)
			return KEY_Q;
		else if (keyCode == KEY_R_CODE)
			return KEY_R;
		else if (keyCode == KEY_S_CODE)
			return KEY_S;
		else if (keyCode == KEY_T_CODE)
			return KEY_T;
		else if (keyCode == KEY_U_CODE)
			return KEY_U;
		else if (keyCode == KEY_V_CODE)
			return KEY_V;
		else if (keyCode == KEY_W_CODE)
			return KEY_W;
		else if (keyCode == KEY_X_CODE)
			return KEY_X;
		else if (keyCode == KEY_Y_CODE )
			return KEY_Y;
		else if (keyCode == KEY_Z_CODE)
			return KEY_Z;
		else if (keyCode == KEY_F1_CODE)
			return KEY_F1;
		else if (keyCode == KEY_F2_CODE)
			return KEY_F2;
		else if (keyCode == KEY_F3_CODE)
			return KEY_F3;
		else if (keyCode == KEY_F4_CODE)
			return KEY_F4;
		else if (keyCode == KEY_F5_CODE)
			return KEY_F5;
		else if (keyCode == KEY_F6_CODE)
			return KEY_F6;
		else if (keyCode == KEY_F7_CODE)
			return KEY_F7;
		else if (keyCode == KEY_F8_CODE)
			return KEY_F8;
		else if (keyCode == KEY_F9_CODE)
			return KEY_F9;
		else if (keyCode == KEY_F10_CODE)
			return KEY_F10;
		else if (keyCode == KEY_F11_CODE)
			return KEY_F11;
		else if (keyCode == KEY_F12_CODE)
			return KEY_F12;
		else if (keyCode == KEY_NUMLOCK_CODE)
			return KEY_NUMLOCK;
		else if (keyCode == KEY_NUMPAD0_CODE)
			return KEY_NUMPAD0;
		else if (keyCode == KEY_NUMPAD1_CODE)
			return KEY_NUMPAD1;
		else if (keyCode == KEY_NUMPAD2_CODE)
			return KEY_NUMPAD2;
		else if (keyCode == KEY_NUMPAD3_CODE)
			return KEY_NUMPAD3;
		else if (keyCode == KEY_NUMPAD4_CODE)
			return KEY_NUMPAD4;
		else if (keyCode == KEY_NUMPAD5_CODE)
			return KEY_NUMPAD5;
		else if (keyCode == KEY_NUMPAD6_CODE)
			return KEY_NUMPAD6;
		else if (keyCode == KEY_NUMPAD7_CODE)
			return KEY_NUMPAD7;
		else if (keyCode == KEY_NUMPAD8_CODE)
			return KEY_NUMPAD8;
		else if (keyCode == KEY_NUMPAD9_CODE)
			return KEY_NUMPAD9;
		else if (keyCode == KEY_NUMPADCOMMA_CODE)
			return KEY_NUMPADCOMMA;
		else if (keyCode == KEY_NUMPADENTER_CODE)
			return KEY_NUMPADENTER;
		else if (keyCode == KEY_NUMPADEQUALS_CODE)
			return KEY_NUMPADEQUALS;
		else if (keyCode == KEY_ADD_CODE)
			return KEY_ADD;
		else if (keyCode == KEY_APOSTROPHE_CODE)
			return KEY_APOSTROPHE;
		else if (keyCode == KEY_APPS_CODE)
			return KEY_APPS;
		else if (keyCode == KEY_AT_CODE)
			return KEY_AT;
		else if (keyCode == KEY_AX_CODE)
			return KEY_AX;
		else if (keyCode == KEY_BACKSPACE_CODE)
			return KEY_BACKSPACE;
		else if (keyCode == KEY_CAPSLOCK_CODE)
			return KEY_CAPSLOCK;
		else if (keyCode == KEY_CLEAR_CODE)
			return KEY_CLEAR;
		else if (keyCode == KEY_COLON_CODE)
			return KEY_COLON;
		else if (keyCode == KEY_COMMA_CODE)
			return KEY_COMMA;
		else if (keyCode == KEY_PERIOD_CODE)
			return KEY_PERIOD;
		else if (keyCode == KEY_DELETE_CODE)
			return KEY_DELETE;
		else if (keyCode == KEY_DIVIDE_CODE)
			return KEY_DIVIDE;
		else if (keyCode == KEY_END_CODE)
			return KEY_END;
		else if (keyCode == KEY_EQUALS_CODE)
			return KEY_EQUALS;
		else if (keyCode == KEY_ESCAPE_CODE)
			return KEY_ESCAPE;
		else if (keyCode == KEY_FUNCTION_CODE)
			return KEY_FUNCTION;
		else if (keyCode == KEY_GRAVE_CODE)
			return KEY_GRAVE;
		else if (keyCode == KEY_HOME_CODE)
			return KEY_HOME;
		else if (keyCode == KEY_INSERT_CODE)
			return KEY_INSERT;
		else if (keyCode == KEY_KANA_CODE)
			return KEY_KANA;
		else if (keyCode == KEY_KANJI_CODE)
			return KEY_KANJI;
		else if (keyCode == KEY_LBRACKET_CODE)
			return KEY_LBRACKET;
		else if (keyCode == KEY_RBRACKET_CODE)
			return KEY_RBRACKET;
		else if (keyCode == KEY_LCONTROL_CODE)
			return KEY_LCONTROL;
		else if (keyCode == KEY_RCONTROL_CODE)
			return KEY_RCONTROL;
		else if (keyCode == KEY_LALT_CODE)
			return KEY_LALT;
		else if (keyCode == KEY_RALT_CODE)
			return KEY_RALT;
		else if (keyCode == KEY_LMETA_CODE)
			return KEY_LMETA;
		else if (keyCode == KEY_RMETA_CODE)
			return KEY_RMETA;
		else if (keyCode == KEY_MINUS_CODE)
			return KEY_MINUS;
		else if (keyCode == KEY_MULTIPLY_CODE)
			return KEY_MULTIPLY;
		else if (keyCode == KEY_PAGE_DOWN_CODE)
			return KEY_PAGE_DOWN;
		else if (keyCode == KEY_NOCONVERT_CODE)
			return KEY_NOCONVERT;
		else if (keyCode == KEY_PAUSE_CODE)
			return KEY_PAUSE;
		else if (keyCode == KEY_PERIOD_CODE)
			return KEY_PERIOD;
		else if (keyCode == KEY_POWER_CODE)
			return KEY_POWER;
		else if (keyCode == KEY_PAGE_UP_CODE)
			return KEY_PAGE_UP;
		else if (keyCode == KEY_RETURN_CODE)
			return KEY_RETURN;
		else if (keyCode == KEY_SCROLLLOCK_CODE)
			return KEY_SCROLLLOCK;
		else if (keyCode == KEY_SECTION_CODE)
			return KEY_SECTION;
		else if (keyCode == KEY_SEMICOLON_CODE)
			return KEY_SEMICOLON;
		else if (keyCode == KEY_FORWARD_SLASH_CODE)
			return KEY_FORWARD_SLASH;
		else if (keyCode == KEY_BACK_SLASH_CODE)
			return KEY_BACK_SLASH;
		else if (keyCode == KEY_SLEEP_CODE)
			return KEY_SLEEP;
		else if (keyCode == KEY_SPACE_CODE)
			return KEY_SPACE;
		else if (keyCode == KEY_STOP_CODE)
			return KEY_STOP;
		else if (keyCode == KEY_SUBTRACT_CODE)
			return KEY_SUBTRACT;
		else if (keyCode == KEY_SYSRQ_CODE)
			return KEY_SYSRQ;
		else if (keyCode == KEY_TAB_CODE)
			return KEY_TAB;
		else if (keyCode == KEY_UNDERSCORE_CODE)
			return KEY_UNDERSCORE;
		else if (keyCode == KEY_UNKNOWN_CODE)
			return KEY_UNKNOWN;
		else if (keyCode == KEY_UP_CODE)
			return KEY_UP;
		else if (keyCode == KEY_DOWN_CODE)
			return KEY_DOWN;
		else if (keyCode == KEY_LEFT_CODE)
			return KEY_LEFT;
		else if (keyCode == KEY_RIGHT_CODE)
			return KEY_RIGHT;
		else if (keyCode == KEY_LSHIFT_CODE)
			return KEY_LSHIFT;
		else if (keyCode == KEY_RSHIFT_CODE)
			return KEY_RSHIFT;
		else
			return false;
	}

	/* The static method to convert a certain key code into a key
	 * code found here */
	public static int convertKeyCode(int keyCode) {
		//Return the correct code
		if (keyCode == GLFW.GLFW_KEY_0)
			return KEY_0_CODE;
		else if (keyCode == GLFW.GLFW_KEY_1)
			return KEY_1_CODE;
		else if (keyCode == GLFW.GLFW_KEY_2)
			return KEY_2_CODE;
		else if (keyCode == GLFW.GLFW_KEY_3)
			return KEY_3_CODE;
		else if (keyCode == GLFW.GLFW_KEY_4)
			return KEY_4_CODE;
		else if (keyCode == GLFW.GLFW_KEY_5)
			return KEY_5_CODE;
		else if (keyCode == GLFW.GLFW_KEY_6)
			return KEY_6_CODE;
		else if (keyCode == GLFW.GLFW_KEY_7)
			return KEY_7_CODE;
		else if (keyCode == GLFW.GLFW_KEY_8)
			return KEY_8_CODE;
		else if (keyCode == GLFW.GLFW_KEY_9)
			return KEY_9_CODE;
		else if (keyCode == GLFW.GLFW_KEY_A)
			return KEY_A_CODE;
		else if (keyCode == GLFW.GLFW_KEY_B)
			return KEY_B_CODE;
		else if (keyCode == GLFW.GLFW_KEY_C)
			return KEY_C_CODE;
		else if (keyCode == GLFW.GLFW_KEY_D)
			return KEY_D_CODE;
		else if (keyCode == GLFW.GLFW_KEY_E)
			return KEY_E_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F)
			return KEY_F_CODE;
		else if (keyCode == GLFW.GLFW_KEY_G)
			return KEY_G_CODE;
		else if (keyCode == GLFW.GLFW_KEY_H)
			return KEY_H_CODE;
		else if (keyCode == GLFW.GLFW_KEY_I)
			return KEY_I_CODE;
		else if (keyCode == GLFW.GLFW_KEY_J)
			return KEY_J_CODE;
		else if (keyCode == GLFW.GLFW_KEY_K)
			return KEY_K_CODE;
		else if (keyCode == GLFW.GLFW_KEY_L)
			return KEY_L_CODE;
		else if (keyCode == GLFW.GLFW_KEY_M)
			return KEY_M_CODE;
		else if (keyCode == GLFW.GLFW_KEY_N)
			return KEY_N_CODE;
		else if (keyCode == GLFW.GLFW_KEY_O)
			return KEY_O_CODE;
		else if (keyCode == GLFW.GLFW_KEY_P)
			return KEY_P_CODE;
		else if (keyCode == GLFW.GLFW_KEY_Q)
			return KEY_Q_CODE;
		else if (keyCode == GLFW.GLFW_KEY_R)
			return KEY_R_CODE;
		else if (keyCode == GLFW.GLFW_KEY_S)
			return KEY_S_CODE;
		else if (keyCode == GLFW.GLFW_KEY_T)
			return KEY_T_CODE;
		else if (keyCode == GLFW.GLFW_KEY_U)
			return KEY_U_CODE;
		else if (keyCode == GLFW.GLFW_KEY_V)
			return KEY_V_CODE;
		else if (keyCode == GLFW.GLFW_KEY_W)
			return KEY_W_CODE;
		else if (keyCode == GLFW.GLFW_KEY_X)
			return KEY_X_CODE;
		else if (keyCode == GLFW.GLFW_KEY_Y)
			return KEY_Y_CODE;
		else if (keyCode == GLFW.GLFW_KEY_Z)
			return KEY_Z_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F1)
			return KEY_F1_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F2)
			return KEY_F2_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F3)
			return KEY_F3_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F4)
			return KEY_F4_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F5)
			return KEY_F5_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F6)
			return KEY_F6_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F7)
			return KEY_F7_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F8)
			return KEY_F8_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F9)
			return KEY_F9_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F10)
			return KEY_F10_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F11)
			return KEY_F11_CODE;
		else if (keyCode == GLFW.GLFW_KEY_F12)
			return KEY_F12_CODE;
		else if (keyCode == GLFW.GLFW_KEY_KP_ADD)
			return KEY_ADD_CODE;
		else if (keyCode == GLFW.GLFW_KEY_APOSTROPHE)
			return KEY_APOSTROPHE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_BACKSPACE)
			return KEY_BACKSPACE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_BACKSLASH)
			return KEY_BACKSLASH_CODE;
		else if (keyCode == GLFW.GLFW_KEY_CAPS_LOCK)
			return KEY_CAPSLOCK_CODE;
		else if (keyCode == GLFW.GLFW_KEY_COMMA)
			return KEY_COMMA_CODE;
		else if (keyCode == GLFW.GLFW_KEY_PERIOD)
			return KEY_PERIOD_CODE;
		else if (keyCode == GLFW.GLFW_KEY_DELETE)
			return KEY_DELETE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_KP_DIVIDE)
			return KEY_DIVIDE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_END)
			return KEY_END_CODE;
		else if (keyCode == GLFW.GLFW_KEY_KP_EQUAL)
			return KEY_EQUALS_CODE;
		else if (keyCode == GLFW.GLFW_KEY_ESCAPE)
			return KEY_ESCAPE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_GRAVE_ACCENT)
			return KEY_GRAVE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_HOME)
			return KEY_HOME_CODE;
		else if (keyCode == GLFW.GLFW_KEY_INSERT)
			return KEY_INSERT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT_BRACKET)
			return KEY_LBRACKET_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT_BRACKET)
			return KEY_RBRACKET_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT_CONTROL)
			return KEY_LCONTROL_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT_CONTROL)
			return KEY_RCONTROL_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT_ALT)
			return KEY_LALT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT_ALT)
			return KEY_RALT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT_SUPER)
			return KEY_LMETA_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT_SUPER)
			return KEY_RMETA_CODE;
		else if (keyCode == GLFW.GLFW_KEY_MINUS)
			return KEY_MINUS_CODE;
		else if (keyCode == GLFW.GLFW_KEY_KP_MULTIPLY)
			return KEY_MULTIPLY_CODE;
		else if (keyCode == GLFW.GLFW_KEY_PAUSE)
			return KEY_PAUSE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_PERIOD)
			return KEY_PERIOD_CODE;
		else if (keyCode == GLFW.GLFW_KEY_PAGE_UP)
			return KEY_PAGE_UP_CODE;
		else if (keyCode == GLFW.GLFW_KEY_PAGE_DOWN)
			return KEY_PAGE_DOWN_CODE;
		else if (keyCode == GLFW.GLFW_KEY_ENTER)
			return KEY_RETURN_CODE;
		else if (keyCode == GLFW.GLFW_KEY_SCROLL_LOCK)
			return KEY_SCROLLLOCK_CODE;
		else if (keyCode == GLFW.GLFW_KEY_SEMICOLON)
			return KEY_SEMICOLON_CODE;
		else if (keyCode == GLFW.GLFW_KEY_SLASH)
			return KEY_FORWARD_SLASH_CODE;
		else if (keyCode == GLFW.GLFW_KEY_BACKSLASH)
			return KEY_BACK_SLASH_CODE;
		else if (keyCode == GLFW.GLFW_KEY_SPACE)
			return KEY_SPACE_CODE;
		else if (keyCode == GLFW.GLFW_KEY_KP_SUBTRACT)
			return KEY_SUBTRACT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_TAB)
			return KEY_TAB_CODE;
		else if (keyCode == GLFW.GLFW_KEY_UNKNOWN)
			return KEY_UNKNOWN_CODE;
		else if (keyCode == GLFW.GLFW_KEY_UP)
			return KEY_UP_CODE;
		else if (keyCode == GLFW.GLFW_KEY_DOWN)
			return KEY_DOWN_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT)
			return KEY_LEFT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT)
			return KEY_RIGHT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_LEFT_SHIFT)
			return KEY_LSHIFT_CODE;
		else if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT)
			return KEY_RSHIFT_CODE;
		else
			return 0;
	}
	
	/* The static method to convert a certain key code into a key
	 * code found in the LWJGL keyboard class here */
	public static int convertKeyCodeLWJGL(int keyCode) {
		//return GLFW.GLFW_the correct code
		if (keyCode == KEY_0_CODE)
			return GLFW.GLFW_KEY_0;
		else if (keyCode == KEY_1_CODE)
			return GLFW.GLFW_KEY_1;
		else if (keyCode == KEY_2_CODE)
			return GLFW.GLFW_KEY_2;
		else if (keyCode == KEY_3_CODE)
			return GLFW.GLFW_KEY_3;
		else if (keyCode == KEY_4_CODE)
			return GLFW.GLFW_KEY_4;
		else if (keyCode == KEY_5_CODE)
			return GLFW.GLFW_KEY_5;
		else if (keyCode == KEY_6_CODE)
			return GLFW.GLFW_KEY_6;
		else if (keyCode == KEY_7_CODE)
			return GLFW.GLFW_KEY_7;
		else if (keyCode == KEY_8_CODE)
			return GLFW.GLFW_KEY_8;
		else if (keyCode == KEY_9_CODE)
			return GLFW.GLFW_KEY_9;
		else if (keyCode == KEY_A_CODE)
			return GLFW.GLFW_KEY_A;
		else if (keyCode == KEY_B_CODE)
			return GLFW.GLFW_KEY_B;
		else if (keyCode == KEY_C_CODE)
			return GLFW.GLFW_KEY_C;
		else if (keyCode == KEY_D_CODE)
			return GLFW.GLFW_KEY_D;
		else if (keyCode == KEY_E_CODE)
			return GLFW.GLFW_KEY_E;
		else if (keyCode == KEY_F_CODE)
			return GLFW.GLFW_KEY_F;
		else if (keyCode == KEY_G_CODE)
			return GLFW.GLFW_KEY_G;
		else if (keyCode == KEY_H_CODE)
			return GLFW.GLFW_KEY_H;
		else if (keyCode == KEY_I_CODE)
			return GLFW.GLFW_KEY_I;
		else if (keyCode == KEY_J_CODE)
			return GLFW.GLFW_KEY_J;
		else if (keyCode == KEY_K_CODE)
			return GLFW.GLFW_KEY_K;
		else if (keyCode == KEY_L_CODE)
			return GLFW.GLFW_KEY_L;
		else if (keyCode == KEY_M_CODE)
			return GLFW.GLFW_KEY_M;
		else if (keyCode == KEY_N_CODE)
			return GLFW.GLFW_KEY_N;
		else if (keyCode == KEY_O_CODE)
			return GLFW.GLFW_KEY_O;
		else if (keyCode == KEY_P_CODE)
			return GLFW.GLFW_KEY_P;
		else if (keyCode == KEY_Q_CODE)
			return GLFW.GLFW_KEY_Q;
		else if (keyCode == KEY_R_CODE)
			return GLFW.GLFW_KEY_R;
		else if (keyCode == KEY_S_CODE)
			return GLFW.GLFW_KEY_S;
		else if (keyCode == KEY_T_CODE)
			return GLFW.GLFW_KEY_T;
		else if (keyCode == KEY_U_CODE)
			return GLFW.GLFW_KEY_U;
		else if (keyCode == KEY_V_CODE)
			return GLFW.GLFW_KEY_V;
		else if (keyCode == KEY_W_CODE)
			return GLFW.GLFW_KEY_W;
		else if (keyCode == KEY_X_CODE)
			return GLFW.GLFW_KEY_X;
		else if (keyCode == KEY_Y_CODE)
			return GLFW.GLFW_KEY_Y;
		else if (keyCode == KEY_Z_CODE)
			return GLFW.GLFW_KEY_Z;
		else if (keyCode == KEY_F1_CODE)
			return GLFW.GLFW_KEY_F1;
		else if (keyCode == KEY_F2_CODE)
			return GLFW.GLFW_KEY_F2;
		else if (keyCode == KEY_F3_CODE)
			return GLFW.GLFW_KEY_F3;
		else if (keyCode == KEY_F4_CODE)
			return GLFW.GLFW_KEY_F4;
		else if (keyCode == KEY_F5_CODE)
			return GLFW.GLFW_KEY_F5;
		else if (keyCode == KEY_F6_CODE)
			return GLFW.GLFW_KEY_F6;
		else if (keyCode == KEY_F7_CODE)
			return GLFW.GLFW_KEY_F7;
		else if (keyCode == KEY_F8_CODE)
			return GLFW.GLFW_KEY_F8;
		else if (keyCode == KEY_F9_CODE)
			return GLFW.GLFW_KEY_F9;
		else if (keyCode == KEY_F10_CODE)
			return GLFW.GLFW_KEY_F10;
		else if (keyCode == KEY_F11_CODE)
			return GLFW.GLFW_KEY_F11;
		else if (keyCode == KEY_F12_CODE)
			return GLFW.GLFW_KEY_F12;
		else if (keyCode == KEY_NUMLOCK_CODE)
			return GLFW.GLFW_KEY_NUM_LOCK;
		else if (keyCode == KEY_ADD_CODE)
			return GLFW.GLFW_KEY_KP_ADD;
		else if (keyCode == KEY_APOSTROPHE_CODE)
			return GLFW.GLFW_KEY_APOSTROPHE;
		else if (keyCode == KEY_BACKSPACE_CODE)
			return GLFW.GLFW_KEY_BACKSPACE;
		else if (keyCode == KEY_BACKSLASH_CODE)
			return GLFW.GLFW_KEY_BACKSLASH;
		else if (keyCode == KEY_CAPSLOCK_CODE)
			return GLFW.GLFW_KEY_CAPS_LOCK;
		else if (keyCode == KEY_COMMA_CODE)
			return GLFW.GLFW_KEY_COMMA;
		else if (keyCode == KEY_PERIOD_CODE)
			return GLFW.GLFW_KEY_PERIOD;
		else if (keyCode == KEY_DELETE_CODE)
			return GLFW.GLFW_KEY_DELETE;
		else if (keyCode == KEY_DIVIDE_CODE)
			return GLFW.GLFW_KEY_KP_DIVIDE;
		else if (keyCode == KEY_END_CODE)
			return GLFW.GLFW_KEY_END;
		else if (keyCode == KEY_EQUALS_CODE)
			return GLFW.GLFW_KEY_KP_EQUAL;
		else if (keyCode == KEY_ESCAPE_CODE)
			return GLFW.GLFW_KEY_ESCAPE;
		else if (keyCode == KEY_GRAVE_CODE)
			return GLFW.GLFW_KEY_GRAVE_ACCENT;
		else if (keyCode == KEY_HOME_CODE)
			return GLFW.GLFW_KEY_HOME;
		else if (keyCode == KEY_INSERT_CODE)
			return GLFW.GLFW_KEY_INSERT;
		else if (keyCode == KEY_LBRACKET_CODE)
			return GLFW.GLFW_KEY_LEFT_BRACKET;
		else if (keyCode == KEY_RBRACKET_CODE)
			return GLFW.GLFW_KEY_RIGHT_BRACKET;
		else if (keyCode == KEY_LCONTROL_CODE)
			return GLFW.GLFW_KEY_LEFT_CONTROL;
		else if (keyCode == KEY_RCONTROL_CODE)
			return GLFW.GLFW_KEY_RIGHT_CONTROL;
		else if (keyCode == KEY_LALT_CODE)
			return GLFW.GLFW_KEY_LEFT_ALT;
		else if (keyCode == KEY_RALT_CODE)
			return GLFW.GLFW_KEY_RIGHT_ALT;
		else if (keyCode == KEY_MINUS_CODE)
			return GLFW.GLFW_KEY_MINUS;
		else if (keyCode == KEY_MULTIPLY_CODE)
			return GLFW.GLFW_KEY_KP_MULTIPLY;
		else if (keyCode == KEY_PAGE_DOWN_CODE)
			return GLFW.GLFW_KEY_PAGE_DOWN;
		else if (keyCode == KEY_PAUSE_CODE)
			return GLFW.GLFW_KEY_PAUSE;
		else if (keyCode == KEY_PERIOD_CODE)
			return GLFW.GLFW_KEY_PERIOD;
		else if (keyCode == KEY_PAGE_UP_CODE)
			return GLFW.GLFW_KEY_PAGE_UP;
		else if (keyCode == KEY_PAGE_DOWN_CODE)
			return GLFW.GLFW_KEY_PAGE_DOWN;
		else if (keyCode == KEY_RETURN_CODE)
			return GLFW.GLFW_KEY_ENTER;
		else if (keyCode == KEY_SCROLLLOCK_CODE)
			return GLFW.GLFW_KEY_SCROLL_LOCK;
		else if (keyCode == KEY_SEMICOLON_CODE)
			return GLFW.GLFW_KEY_SEMICOLON;
		else if (keyCode == KEY_FORWARD_SLASH_CODE)
			return GLFW.GLFW_KEY_SLASH;
		else if (keyCode == KEY_BACK_SLASH_CODE)
			return GLFW.GLFW_KEY_BACKSLASH;
		else if (keyCode == KEY_SPACE_CODE)
			return GLFW.GLFW_KEY_SPACE;
		else if (keyCode == KEY_SUBTRACT_CODE)
			return GLFW.GLFW_KEY_KP_SUBTRACT;
		else if (keyCode == KEY_LMETA_CODE)
			return GLFW.GLFW_KEY_LEFT_SUPER;
		else if (keyCode == KEY_RMETA_CODE)
			return GLFW.GLFW_KEY_RIGHT_SUPER;
		else if (keyCode == KEY_TAB_CODE)
			return GLFW.GLFW_KEY_TAB;
		else if (keyCode == KEY_UNKNOWN_CODE)
			return GLFW.GLFW_KEY_UNKNOWN;
		else if (keyCode == KEY_UP_CODE)
			return GLFW.GLFW_KEY_UP;
		else if (keyCode == KEY_DOWN_CODE)
			return GLFW.GLFW_KEY_DOWN;
		else if (keyCode == KEY_LEFT_CODE)
			return GLFW.GLFW_KEY_LEFT;
		else if (keyCode == KEY_RIGHT_CODE)
			return GLFW.GLFW_KEY_RIGHT;
		else if (keyCode == KEY_LSHIFT_CODE)
			return GLFW.GLFW_KEY_LEFT_SHIFT;
		else if (keyCode == KEY_RSHIFT_CODE)
			return GLFW.GLFW_KEY_RIGHT_SHIFT;
		else
			return 0;
	}

	/* The static method used to set a certain key's boolean value of whether it is pressed */
	public static void setKeyDown(int keyCode, boolean keyDown) {
		if (keyCode == KEY_0_CODE)
			KEY_0 = keyDown;
		else if (keyCode == KEY_1_CODE)
			KEY_1 = keyDown;
		else if (keyCode == KEY_2_CODE)
			KEY_2 = keyDown;
		else if (keyCode == KEY_3_CODE)
			KEY_3 = keyDown;
		else if (keyCode == KEY_4_CODE)
			KEY_4 = keyDown;
		else if (keyCode == KEY_5_CODE)
			KEY_5 = keyDown;
		else if (keyCode == KEY_6_CODE)
			KEY_6 = keyDown;
		else if (keyCode == KEY_7_CODE)
			KEY_7 = keyDown;
		else if (keyCode == KEY_8_CODE)
			KEY_8 = keyDown;
		else if (keyCode == KEY_9_CODE)
			KEY_9 = keyDown;
		else if (keyCode == KEY_A_CODE)
			KEY_A = keyDown;
		else if (keyCode == KEY_B_CODE)
			KEY_B = keyDown;
		else if (keyCode == KEY_C_CODE)
			KEY_C = keyDown;
		else if (keyCode == KEY_D_CODE)
			KEY_D = keyDown;
		else if (keyCode == KEY_E_CODE)
			KEY_E = keyDown;
		else if (keyCode == KEY_F_CODE)
			KEY_F = keyDown;
		else if (keyCode == KEY_G_CODE)
			KEY_G = keyDown;
		else if (keyCode == KEY_H_CODE)
			KEY_H = keyDown;
		else if (keyCode == KEY_I_CODE)
			KEY_I = keyDown;
		else if (keyCode == KEY_J_CODE)
			KEY_J = keyDown;
		else if (keyCode == KEY_K_CODE)
			KEY_K = keyDown;
		else if (keyCode == KEY_L_CODE)
			KEY_L = keyDown;
		else if (keyCode == KEY_M_CODE)
			KEY_M = keyDown;
		else if (keyCode == KEY_N_CODE)
			KEY_N = keyDown;
		else if (keyCode == KEY_O_CODE)
			KEY_O = keyDown;
		else if (keyCode == KEY_P_CODE)
			KEY_P = keyDown;
		else if (keyCode == KEY_Q_CODE)
			KEY_Q = keyDown;
		else if (keyCode == KEY_R_CODE)
			KEY_R = keyDown;
		else if (keyCode == KEY_S_CODE)
			KEY_S = keyDown;
		else if (keyCode == KEY_T_CODE)
			KEY_T = keyDown;
		else if (keyCode == KEY_U_CODE)
			KEY_U = keyDown;
		else if (keyCode == KEY_V_CODE)
			KEY_V = keyDown;
		else if (keyCode == KEY_W_CODE)
			KEY_W = keyDown;
		else if (keyCode == KEY_X_CODE)
			KEY_X = keyDown;
		else if (keyCode == KEY_Y_CODE)
			KEY_Y = keyDown;
		else if (keyCode == KEY_Z_CODE)
			KEY_Z = keyDown;
		else if (keyCode == KEY_F1_CODE)
			KEY_F1 = keyDown;
		else if (keyCode == KEY_F2_CODE)
			KEY_F2 = keyDown;
		else if (keyCode == KEY_F3_CODE)
			KEY_F3 = keyDown;
		else if (keyCode == KEY_F4_CODE)
			KEY_F4 = keyDown;
		else if (keyCode == KEY_F5_CODE)
			KEY_F5 = keyDown;
		else if (keyCode == KEY_F6_CODE)
			KEY_F6 = keyDown;
		else if (keyCode == KEY_F7_CODE)
			KEY_F7 = keyDown;
		else if (keyCode == KEY_F8_CODE)
			KEY_F8 = keyDown;
		else if (keyCode == KEY_F9_CODE)
			KEY_F9 = keyDown;
		else if (keyCode == KEY_F10_CODE)
			KEY_F10 = keyDown;
		else if (keyCode == KEY_F11_CODE)
			KEY_F11 = keyDown;
		else if (keyCode == KEY_F12_CODE)
			KEY_F12 = keyDown;
		else if (keyCode == KEY_NUMLOCK_CODE)
			KEY_NUMLOCK = keyDown;
		else if (keyCode == KEY_NUMPAD0_CODE)
			KEY_NUMPAD0 = keyDown;
		else if (keyCode == KEY_NUMPAD1_CODE)
			KEY_NUMPAD1 = keyDown;
		else if (keyCode == KEY_NUMPAD2_CODE)
			KEY_NUMPAD2 = keyDown;
		else if (keyCode == KEY_NUMPAD3_CODE)
			KEY_NUMPAD3 = keyDown;
		else if (keyCode == KEY_NUMPAD4_CODE)
			KEY_NUMPAD4 = keyDown;
		else if (keyCode == KEY_NUMPAD5_CODE)
			KEY_NUMPAD5 = keyDown;
		else if (keyCode == KEY_NUMPAD6_CODE)
			KEY_NUMPAD6 = keyDown;
		else if (keyCode == KEY_NUMPAD7_CODE)
			KEY_NUMPAD7 = keyDown;
		else if (keyCode == KEY_NUMPAD8_CODE)
			KEY_NUMPAD8 = keyDown;
		else if (keyCode == KEY_NUMPAD9_CODE)
			KEY_NUMPAD9 = keyDown;
		else if (keyCode == KEY_ADD_CODE)
			KEY_ADD = keyDown;
		else if (keyCode == KEY_AT_CODE)
			KEY_AT = keyDown;
		else if (keyCode == KEY_BACKSPACE_CODE)
			KEY_BACKSPACE = keyDown;
		else if (keyCode == KEY_CAPSLOCK_CODE)
			KEY_CAPSLOCK = keyDown;
		else if (keyCode == KEY_CLEAR_CODE)
			KEY_CLEAR = keyDown;
		else if (keyCode == KEY_COLON_CODE)
			KEY_COLON = keyDown;
		else if (keyCode == KEY_COMMA_CODE)
			KEY_COMMA = keyDown;
		else if (keyCode == KEY_PERIOD_CODE)
			KEY_PERIOD = keyDown;
		else if (keyCode == KEY_DELETE_CODE)
			KEY_DELETE = keyDown;
		else if (keyCode == KEY_DIVIDE_CODE)
			KEY_DIVIDE = keyDown;
		else if (keyCode == KEY_END_CODE)
			KEY_END = keyDown;
		else if (keyCode == KEY_EQUALS_CODE)
			KEY_EQUALS = keyDown;
		else if (keyCode == KEY_ESCAPE_CODE)
			KEY_ESCAPE = keyDown;
		else if (keyCode == KEY_GRAVE_CODE)
			KEY_GRAVE = keyDown;
		else if (keyCode == KEY_HOME_CODE)
			KEY_HOME = keyDown;
		else if (keyCode == KEY_INSERT_CODE)
			KEY_INSERT = keyDown;
		else if (keyCode == KEY_KANA_CODE)
			KEY_KANA = keyDown;
		else if (keyCode == KEY_KANJI_CODE)
			KEY_KANJI = keyDown;
		else if (keyCode == KEY_LBRACKET_CODE)
			KEY_LBRACKET = keyDown;
		else if (keyCode == KEY_RBRACKET_CODE)
			KEY_RBRACKET = keyDown;
		else if (keyCode == KEY_LCONTROL_CODE)
			KEY_LCONTROL = keyDown;
		else if (keyCode == KEY_RCONTROL_CODE)
			KEY_RCONTROL = keyDown;
		else if (keyCode == KEY_RALT_CODE)
			KEY_RALT = keyDown;
		else if (keyCode == KEY_LALT_CODE)
			KEY_LALT = keyDown;
		else if (keyCode == KEY_RMETA_CODE)
			KEY_RMETA = keyDown;
		else if (keyCode == KEY_LMETA_CODE)
			KEY_LMETA = keyDown;
		else if (keyCode == KEY_MINUS_CODE)
			KEY_MINUS = keyDown;
		else if (keyCode == KEY_MULTIPLY_CODE)
			KEY_MULTIPLY = keyDown;
		else if (keyCode == KEY_PAGE_UP_CODE)
			KEY_PAGE_UP = keyDown;
		else if (keyCode == KEY_PAUSE_CODE)
			KEY_PAUSE = keyDown;
		else if (keyCode == KEY_PERIOD_CODE)
			KEY_PERIOD = keyDown;
		else if (keyCode == KEY_PAGE_UP_CODE)
			KEY_PAGE_UP = keyDown;
		else if (keyCode == KEY_RETURN_CODE)
			KEY_RETURN = keyDown;
		else if (keyCode == KEY_SCROLLLOCK_CODE)
			KEY_SCROLLLOCK = keyDown;
		else if (keyCode == KEY_SEMICOLON_CODE)
			KEY_SEMICOLON = keyDown; 
		else if (keyCode == KEY_FORWARD_SLASH_CODE)
			KEY_FORWARD_SLASH = keyDown;
		else if (keyCode == KEY_BACK_SLASH_CODE)
			KEY_BACK_SLASH = keyDown;
		else if (keyCode == KEY_SPACE_CODE)
			KEY_SPACE = keyDown;
		else if (keyCode == KEY_STOP_CODE)
			KEY_STOP = keyDown;
		else if (keyCode == KEY_SUBTRACT_CODE)
			KEY_SUBTRACT = keyDown;
		else if (keyCode == KEY_TAB_CODE)
			KEY_TAB = keyDown;
		else if (keyCode == KEY_UNDERSCORE_CODE)
			KEY_UNDERSCORE = keyDown;
		else if (keyCode == KEY_UP_CODE)
			KEY_UP = keyDown;
		else if (keyCode == KEY_DOWN_CODE)
			KEY_DOWN = keyDown;
		else if (keyCode == KEY_LEFT_CODE)
			KEY_LEFT = keyDown;
		else if (keyCode == KEY_RIGHT_CODE)
			KEY_RIGHT = keyDown;
		else if (keyCode == KEY_LSHIFT_CODE)
			KEY_LSHIFT = keyDown;
		else if (keyCode == KEY_RSHIFT_CODE)
			KEY_RSHIFT = keyDown;
	}

	/* The static method called when a key is pressed */
	public static void onKeyPressed(KeyboardEvent event) {
		//Set the key down value
		setKeyDown(event.keyCode, true);
	}

	/* The static method called when a key is released */
	public static void onKeyReleased(KeyboardEvent event) {
		//Set the key down value
		setKeyDown(event.keyCode, false);
	}
	
}