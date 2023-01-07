package bouncetales;

import bouncetales.ext.rsc.ImageMap;
import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectUtils;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/* renamed from: m */
public final class BounceGame {

	public static final int CONTROLLER_NORMAL = 0;
	public static final int CONTROLLER_CANNON = 1;
	public static final int CONTROLLER_DISABLED = 2;
	public static final int CONTROLLER_FROZEN = 3;

	/* renamed from: A */
	private static int mainMenuReturnUI = 17;

	/* renamed from: D */
	private static int objectCount;

	/* renamed from: E */
	private static int eventCount;

	/* renamed from: F */
	private static int f240F;

	/* renamed from: G */
	private static int bonusLevelEggLimit;

	/* renamed from: H */
	private static int stolenColorsAnimationCountdown;

	/* renamed from: I */
	private static int stolenColorsFlashCountdown;

	/* renamed from: a */
	private static byte cheatComboIndex;

	/* renamed from: a */
	public static int levelTimer;

	/* renamed from: a */
	public static EggObject enemyDeadEgg;

	/* renamed from: a */
	public static BounceObject bounceObj;

	/* renamed from: a */
	public static GameObject rootLevelObj;

	/* renamed from: a */
	private static String lastFieldMsg;

	/* renamed from: a */
	public static Random mRNG = new Random(System.currentTimeMillis());

	/* renamed from: a */
	public static Graphics ballGraphics;

	/* renamed from: a */
	public static Image ballFramebuffer;

	//LEVEL ACTORS
	private static EventObject[] events;

	private static GameObject[] levelObjects;

	public static GameObject[] cannonModels;

	public static CannonObject currentCannon;

	/* renamed from: j */
	private static int[] f310j = {420, 426, 402, 408};

	/* renamed from: a */
	public static ParticleObject winParticle = new ParticleObject(20, 0, 0, 0, 0, 35, 4, f310j, 1840, -4);

	/* renamed from: a */
	public static boolean f255a;

	/* renamed from: a */
	private static final byte[] CHEAT_COMBO_ALL_UNLOCK = {KeyCode.NUM0, KeyCode.NUM0, KeyCode.NUM0};

	/* renamed from: a */
	private static final float[] EGG_SCORE_MULTIPLIER_BY_LEVEL = {
		0.937f,
		0.969f,
		0.659f,
		0.937f,
		1.412f,
		0.969f,
		2.121f,
		1.298f,
		1.298f,
		1.011f,
		1.298f,
		1.921f,
		1.298f,
		0.712f,
		1.195f
	};

	/* renamed from: a */
	public static int[] ballFramebufferRGB;

	/* renamed from: a */
	private static String[] fieldMessageParam = null;

	/* renamed from: a */
	private static Image[] parallaxImagesRegColors;

	/* renamed from: a */
	public static final short[] SCRIPT_MESSAGE_IDS = {48, 44, 43, 46, 49, 45, 47, 36, 84, 19, 18, 17, 4, 12, 13, 11, 14, 15, 83, 80, 37, 85, 38, 39, 87, 2, 3, 35, 16, 0, 1, 20, 24, 25, 32, 26, 27, 28, 29, 33, 30, 31, 21, 22, 34, 23, 86, 81, 82, 5, 6, 8, 7, 41, 9, 10, 40, 42, 51, 52, 53, 50, 54, 55, 56, 57, 58, 59, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 60, 61, 62, 63, 64, 88, 89, 90};

	/* renamed from: l */
	private static int[] f317l = {390, 414, 396};

	/* renamed from: f */
	private static int[] f292f = {526, 516, 531, 521};

	private static int[] f287e = {536};

	private static int[] f313k = {420, 426};

	private static int[] f297g = {390, 420};

	private static int[] f302h = {420, 426};

	private static int[] f306i = {414, 396, 420};

	private static int[] f321m = {437, 432};

	/* renamed from: b */
	public static int exitWaitTimer;

	/* renamed from: b */
	public static Graphics f265b;

	/* renamed from: b */
	public static Image f266b;

	/* renamed from: b */
	public static ParticleObject f267b = new ParticleObject(20, 0, -200, 0, 0, 0, 0, f292f, 800, 7);

	/* renamed from: b */
	public static boolean levelPaused;

	/* renamed from: b */
	private static final byte[] CHEAT_COMBO_ALL_COMPLETE = {KeyCode.NUM1, KeyCode.NUM1, KeyCode.NUM1};

	/* renamed from: b */
	public static int[] f270b;

	/* renamed from: b */
	private static Image[] parallaxImagesStolenColors;

	/* renamed from: b */
	public static short[] SIN_COS_TABLE = new short[360];

	/* renamed from: c */
	public static int f274c;

	/* renamed from: c */
	public static ParticleObject swimParticle = new ParticleObject(150, 0, 80, 0, 0, 0, 1, f287e, 4000, 7);

	/* renamed from: c */
	public static boolean reqCameraSnap = false;

	/* renamed from: c */
	private static int[] BONUS_LEVEL_INFO = {
		LevelID.SECRET_STALKWAY, 60,
		LevelID.TUNNEL_OF_TREASURES, 200,
		LevelID.FANTASTIC_FAIR, 400
	};

	/* renamed from: c */
	private static short[] TROPHY_IMAGE_IDS = {314, 315, 389};

	/* renamed from: d */
	public static final int f279d = 0;

	/* renamed from: d */
	public static ParticleObject f280d = new ParticleObject(10, 0, 0, 0, 0, 0, 6, f313k, 1000, -5);

	/* renamed from: d */
	public static boolean isSuperBounceUnlocked;

	/* renamed from: d */
	private static final int[] FORME_UNLOCK_LEVELS = {3, 8};

	/* renamed from: d */
	private static final short[] LEVEL_EGG_TROPHY_REQUIREMENTS = {
		30, 29, 26,
		30, 28, 26,
		30, 25, 21,
		30, 25, 20,
		30, 30, 30,
		30, 28, 25,
		30, 28, 22,
		30, 27, 20,
		30, 27, 24,
		30, 30, 30,
		30, 29, 27,
		30, 28, 19,
		30, 25, 20,
		30, 25, 20,
		30, 30, 30
	};

	/* renamed from: e */
	public static int currentLevel;

	/* renamed from: e */
	public static ParticleObject f285e = new ParticleObject(10, 0, 0, 0, 0, 30, 2, f297g, 800, -1);

	/* renamed from: e */
	private static boolean isTextRightToLeft = StringManager.getMessage(90).equals("1");

	/* renamed from: e */
	private static final short[] LEVEL_TIMER_TROPHY_REQUIREMENTS = {
		30, 40, 50,
		36, 45, 55,
		35, 40, 48,
		32, 42, 50,
		9999, 9999, 9999,
		34, 45, 60,
		75, 85, 95,
		42, 46, 55,
		45, 55, 65,
		9999, 9999, 9999,
		45, 50, 58,
		60, 70, 80,
		48, 54, 60,
		24, 34, 44,
		9999, 9999, 9999
	};

	/* renamed from: f */
	public static int eggCount;

	/* renamed from: f */
	public static ParticleObject f290f = new ParticleObject(50, 0, 0, 0, 0, 85, 3, f302h, 540, -2);

	/* renamed from: f */
	private static final boolean enableCheats;

	/* renamed from: f */
	private static short[] levelSaveData = new short[60];

	/* renamed from: g */
	public static int checkpointPosX;

	/* renamed from: h */
	public static int checkpointPosY;

	/* renamed from: g */
	public static ParticleObject enemyDeathParticle = new ParticleObject(50, 0, 0, 0, 0, 35, 4, f306i, 1840, -3);

	/* renamed from: g */
	private static boolean isFieldMessageShowing;

	/* renamed from: g */

 /* renamed from: g */
	private static final short[] LEVEL_COVER_ART_IMAGE_IDS = {
		359,
		363,
		364,
		365,
		328,
		366,
		367,
		368,
		369,
		329,
		370,
		360,
		361,
		362,
		330
	};

	/* renamed from: h */
	public static ParticleObject f300h = new ParticleObject(24, 0, 0, 0, 0, 35, 4, f317l, 2040, -6);

	/* renamed from: h */
	private static boolean isBlockingEvent;

	/* renamed from: h */

 /* renamed from: h */
	private static final short[] LEVEL_NAME_MESSAGE_IDS = {
		MessageID.LEVEL_MLHAVE_RANO,
		MessageID.LEVEL_NEVLIDNI_KAMARADI,
		MessageID.LEVEL_HLEDANI_ODPOVEDI,
		MessageID.LEVEL_BUMPY_CRACKS,
		MessageID.LEVEL_TAJNE_SLEDOVANI,
		MessageID.LEVEL_DO_DOLU,
		MessageID.LEVEL_TEMNA_CESTICKA,
		MessageID.LEVEL_BURACIVE_ZVUKY,
		MessageID.LEVEL_LAPEN_VE_STROJI,
		MessageID.LEVEL_TUNEL_POKLADU,
		MessageID.LEVEL_ZLOLAJNY_CIRKUS,
		MessageID.LEVEL_LOVECKE_BARVY,
		MessageID.LEVEL_SKORO_TAM,
		MessageID.LEVEL_POSLEDNI_CESTA,
		MessageID.LEVEL_FANTASTICKY_PARK
	};

	/* renamed from: i */
	public static ParticleObject waterParticle = new ParticleObject(150, 0, 0, 0, 0, 0, 7, f321m, 2000, 15);

	/* renamed from: i */
	private static boolean reqReloadFieldMsg = false;

	/* renamed from: i */
 /* renamed from: i */
	private static short[] f307i = {388};

	/* renamed from: j */
	private static int totalGameTime;

	/* renamed from: j */
	private static boolean hasLoadingProgressBar = false;

	/* renamed from: j */
	private static short[] f311j = {251};

	/* renamed from: k */
	private static int selectedLevelId = 0;

	/* renamed from: k */

 /* renamed from: k */
	private static short[] f314k = {252};

	/* renamed from: l */
	private static int f315l = 0;

	/* renamed from: l */
	private static boolean isFlashToOtherColorMode;

	/* renamed from: l */
	private static short[] f318l = new short[0];

	/* renamed from: m */
	private static int f319m = 0;

	/* renamed from: m */
	private static boolean isColorsAreStolen;

	/* renamed from: m */
 /* renamed from: m */
	private static short[] f322m = {373};

	/* renamed from: n */
	private static int f323n = 0;

	/* renamed from: n */
	private static int[] fieldMessageQueue = new int[5];

	/* renamed from: n */
	private static short[] f325n = {161};

	/* renamed from: o */
	private static int lastMainMenuOption = 0;

	/* renamed from: o */
	private static final int[] SPLASH_SCREEN_LAYOUT_RESIDS = {7};

	/* renamed from: o */
	private static short[] f328o = {159, 160};

	/* renamed from: p */
	private static int calcScore;

	/* renamed from: p */
	private static final int[] SPLASH_IMAGE_IDS = {1};

	/* renamed from: p */
	private static short[] f331p = new short[0];

	/* renamed from: q */
	private static int f332q = 129;

	/* renamed from: q */
	private static final int[] SPLASH_SCREEN_DURATIONS = {100};

	/* renamed from: q */
	private static short[] f334q = {352};

	/* renamed from: r */
	private static int f335r = 40;

	/* renamed from: r */
	private static final int[] BACKGROUND_COLORS = {0xFFFFFF};

	/* renamed from: r */
	private static short[] f337r = {113};

	/* renamed from: s */
	private static int f338s = 5;

	/* renamed from: s */
	private static int[] paralaxXOffsets = new int[10];

	/* renamed from: s */
	private static short[] f340s = {114};

	/* renamed from: t */
	private static int f341t = 56;

	/* renamed from: t */
	private static int[] parallaxYOffsets = new int[10];

	/* renamed from: t */
	private static short[] f343t = {353};

	/* renamed from: u */
	private static int f344u = 20;

	/* renamed from: u */
	private static int[] parallaxImageIndices = new int[10];

	/* renamed from: u */
	private static short[] ALL_PARALLAX_IMAGE_IDS = {388, 373, 145, 313, 265, 157, 174, 55, 345, 243, 78, 317, 176, 267};

	/* renamed from: v */
	private static int f347v = 40;

	/* renamed from: v */
	private static int[] xluSoftkeyBarXs = new int[4];

	public static final int CANNON_LEVEL_INDEX = 15;

	private static short[] LEVEL_RESIDS = {
		39,
		40,
		41,
		42,
		51,
		43,
		44,
		45,
		46,
		52,
		47,
		48,
		49,
		50,
		53,
		54
	};

	/* renamed from: w */
	private static int f350w = 0;

	/* renamed from: w */
	private static int[] xluSoftkeyBarYs = new int[4];

	/* renamed from: w */
	private static short[] NUMBER_FONT_IMAGE_IDS = {90, 91, 92, 93, 94, 95, 96, 97, 98, 99};

	/* renamed from: x */
	private static int f353x = GameRuntime.currentWidth;

	/* renamed from: y */
	private static int f354y = GameRuntime.currentHeight;

	/* renamed from: z */
	private static int fieldMessagePointer;

	/* renamed from: B */
	private int loadingProgressBar = 0;

	/* renamed from: C */
	private int curSplashId;

	/* renamed from: J */
	private int timerChallengeTrophy = -1;

	/* renamed from: K */
	private int collectionChallengeTrophy = -1;

	/* renamed from: a */
	private long splashScreenStartTime;

	/* renamed from: a */
	private UILayout drawUI = null;

	/* renamed from: b */
	private final UILayout ui = new UILayout();

	/* renamed from: i */
	private int gameMainState = 1;

	/* renamed from: k */
	private boolean isLevelActive = false;

	/* renamed from: n */
	private boolean wasFinalLevelJustBeaten = false;

	/* renamed from: o */
	private boolean wasSuperBounceJustUnlocked = false;

	/* renamed from: p */
	private boolean reqQuitLevelAfterFieldMessage = false;

	/* renamed from: q */
	private boolean highScoreBeaten = false;

	static {
		enableCheats = GameRuntime.getAppFlag("Cheats");
		generateSinCosTable();
	}

	/* renamed from: a */
	public static int getUnlockedFormeCount() {
		int i = 0;
		if (wasLevelBeaten(FORME_UNLOCK_LEVELS[0]) || (currentLevel == FORME_UNLOCK_LEVELS[0] && EventObject.eventVars[2] > 0)) {
			i = 1;
		}
		if (wasLevelBeaten(FORME_UNLOCK_LEVELS[1]) || (currentLevel == FORME_UNLOCK_LEVELS[1] && EventObject.eventVars[2] > 0)) {
			return 2;
		}
		return i;
	}

	/* renamed from: a */
	private static int drawStylizedNumber(int x, int y, int value, int anchor, boolean allowSingleDigit) {
		int newDrawnWidth;
		boolean isSingleDigit = value < 10 && allowSingleDigit;
		int drawnWidth = 0;
		int digitIndex = anchor == Graphics.LEFT ? 0 : 1; //EXTREMELY hackily coded, only works for 2 digit integers cause it skips the 1st digit for left alignment
		int remainder = value;
		int xOffset = x;
		while (digitIndex < 2) {
			if (remainder == 0) {
				if (digitIndex == 1) {
					GameRuntime.drawImageResAnchored(xOffset, y, NUMBER_FONT_IMAGE_IDS[0], Graphics.TOP | Graphics.RIGHT);
				}
				newDrawnWidth = drawnWidth + GameRuntime.getImageMapParam(NUMBER_FONT_IMAGE_IDS[0], ImageMap.PARAM_WIDTH);
			} else {
				newDrawnWidth = drawnWidth;
				while (remainder != 0) {
					short imageId = NUMBER_FONT_IMAGE_IDS[remainder % 10];
					if (digitIndex == 1) {
						GameRuntime.drawImageResAnchored(xOffset - newDrawnWidth, y, imageId, Graphics.TOP | Graphics.RIGHT);
					}
					remainder /= 10;
					newDrawnWidth += GameRuntime.getImageMapParam(imageId, ImageMap.PARAM_WIDTH);
				}
			}
			if (digitIndex == 0) {
				xOffset += newDrawnWidth;
				newDrawnWidth = 0;
				remainder = value;
			}
			digitIndex++;
			drawnWidth = newDrawnWidth;
		}
		if (!isSingleDigit) {
			return drawnWidth;
		}
		GameRuntime.drawImageResAnchored(xOffset - drawnWidth, y, NUMBER_FONT_IMAGE_IDS[0], Graphics.TOP | Graphics.RIGHT); //leading zero
		return GameRuntime.getImageMapParam(NUMBER_FONT_IMAGE_IDS[0], ImageMap.PARAM_WIDTH) + drawnWidth;
	}

	/* renamed from: a */
	private static String getLevelChapterNumber(int levelId) {
		int countOfBonusChaptersBefore = 0;
		int bonusStructIdx = 0;
		while (bonusStructIdx < BONUS_LEVEL_INFO.length && levelId >= BONUS_LEVEL_INFO[bonusStructIdx]) {
			countOfBonusChaptersBefore++;
			bonusStructIdx += 2;
		}
		return isBonusLevel(levelId) ? String.valueOf(countOfBonusChaptersBefore) : String.valueOf((levelId + 1) - countOfBonusChaptersBefore);
	}

	/* renamed from: a */
	private static void generateSinCosTable() {
		int curve = 0;
		int tangent = 57 * 360;
		for (int angle = 0; angle < 360; angle++) {
			int sin = curve / 57;
			SIN_COS_TABLE[angle] = (short) sin;
			tangent -= sin;
			curve += tangent / 57;
		}
	}

	/* renamed from: a */
	public static final void pushFieldMessage(int msgId) {
		if (fieldMessagePointer < 5) {
			fieldMessageQueue[fieldMessagePointer] = msgId;
			fieldMessagePointer++;
		}
	}

	public static final void setPlayerState(int state) {
		EventObject.eventVars[0] = state;
	}

	public static final int getPlayerState() {
		return EventObject.eventVars[0];
	}

	/* renamed from: a */
	private static void drawLevelSelectUI(int x, int centerX, int levelId, int bottomY, int i5) {
		int b = getLevelType(levelId);
		if (b == 0) {
			GameRuntime.drawImageRes(x, centerX, 8);
		} else if (b == 2) {
			GameRuntime.drawImageRes(x, centerX, 380);
		}
		GameRuntime.drawImageRes(x, centerX, LEVEL_COVER_ART_IMAGE_IDS[levelId]);
		String[] printfParams = new String[1];
		GameRuntime.setTextStyle(-3, 1);
		GameRuntime.setTextColor(0, 0);
		if (!isLevelUnlocked(levelId)) {
			GameRuntime.drawImageRes(x, centerX, 149);
			if (isBonusLevel(levelId)) {
				int i6 = 0;
				for (int i7 = 0; i7 < BONUS_LEVEL_INFO.length; i7 += 2) {
					if (levelId == BONUS_LEVEL_INFO[i7]) {
						i6 = BONUS_LEVEL_INFO[i7 + 1];
					}
				}
				String unlockRequirementMsg = StringManager.getMessage(MessageID.NEED_COLLECT_COUNT, i6);
				int a2 = GameRuntime.getStrRenderWidth(-3, unlockRequirementMsg, 0, unlockRequirementMsg.length()) + 23 + 5;
				int i8 = (GameRuntime.currentWidth >> 1) - (a2 >> 1);
				int i9 = a2 + i8;
				if (isTextRightToLeft) {
					GameRuntime.drawImageResAnchored(i9, i5, 102, 24);
					GameRuntime.drawText(unlockRequirementMsg, 0, unlockRequirementMsg.length(), i8, i5, 20);
				} else {
					GameRuntime.drawImageResAnchored(i8, i5, 102, 20);
					GameRuntime.drawText(unlockRequirementMsg, 0, unlockRequirementMsg.length(), i8 + 23 + 5, i5, 20);
				}
			}
		} else {
			int a3 = GameRuntime.getFontHeight(-3) + 1;
			int a4 = GameRuntime.getFontHeight(-3) + 23 + 3;
			String a5 = StringManager.getMessage(MessageID.UI_SCORE, "9999");
			int a6 = GameRuntime.getStrRenderWidth(-3, a5, 0, a5.length());
			int myScore = getLevelLocalHighScore(levelId);
			if (!(myScore > 0)) {
				myScore = 0;
			}
			int i11 = ((GameRuntime.currentWidth >> 1) - (a6 >> 1)) - 6;
			int a7 = GameRuntime.getStrRenderWidth(-3, "00/00", 0, "00/00".length()) + 23 + 11;
			String a8 = StringManager.getMessage(MessageID.UI_SCORE, myScore);
			if (isTextRightToLeft) {
				GameRuntime.drawText(a8, 0, a8.length(), i11 + a7 + 23, i5, 24);
			} else {
				GameRuntime.drawText(a8, 0, a8.length(), i11, i5, 20);
			}
			if (!isBonusLevel(levelId)) {
				printfParams[0] = getLevelEggCount(levelId) + "/30";
				String str = printfParams[0];
				if (isTextRightToLeft) {
					GameRuntime.drawImageResAnchored(i11 + a7, i5 + a3, 102, 20);
					GameRuntime.drawText(str, 0, str.length(), (i11 - 11) + a7, i5 + a3 + 11, 10);
				} else {
					GameRuntime.drawImageResAnchored(i11, i5 + a3, 102, 20);
					GameRuntime.drawText(str, 0, str.length(), i11 + 23 + 11, i5 + a3 + 11, 6);
				}
			}
			if (wasLevelBeaten(LevelID.FINAL_RIDE) && !isBonusLevel(levelId)) {
				int d = getCollectionChallengeRank(levelId);
				int i12 = d > -1 ? TROPHY_IMAGE_IDS[d] : d;
				int e = getTimerChallengeRank(levelId);
				int i13 = e > -1 ? TROPHY_IMAGE_IDS[e] : e;
				String timer = formatGameTimer(getLevelClearTime(levelId));
				if (isTextRightToLeft) {
					GameRuntime.drawImageResAnchored(i11 + a7, i5 + a4, 103, 20);
					GameRuntime.drawText(timer, 0, timer.length(), (i11 - 11) + a7, i5 + a4 + 11, 10);
				} else {
					GameRuntime.drawImageResAnchored(i11, i5 + a4, 103, 20);
					GameRuntime.drawText(timer, 0, timer.length(), i11 + 23 + 11, i5 + a4 + 11, 6);
				}
				int a9 = GameRuntime.getStrRenderWidth(-3, "00:00", 0, "00:00".length()) + 23 + 11;
				if (isTextRightToLeft) {
					if (i12 > -1) {
						GameRuntime.drawImageResAnchored(i11, i5 + a3, i12, 24);
					}
					if (i13 > -1) {
						GameRuntime.drawImageResAnchored(i11, i5 + a4, i13, 12);
					}
				} else {
					if (i12 > -1) {
						GameRuntime.drawImageResAnchored(i11 + a9 + 11, i5 + a3, i12, 20);
					}
					if (i13 > -1) {
						GameRuntime.drawImageResAnchored(a9 + i11 + 11, i5 + a4, i13, 20);
					}
				}
			}
		}
		GameRuntime.setTextStyle(-2, 3);
		GameRuntime.setTextColor(0, 0xFF7800);
		GameRuntime.setTextColor(1, 0);
		if (isBonusLevel(levelId)) {
			String numStr = StringManager.getMessage(MessageID.UI_CHAPTERNO_BONUS, getLevelChapterNumber(levelId));
			GameRuntime.drawText(numStr, 0, numStr.length(), x, bottomY - GameRuntime.getFontHeight(GameRuntime.getCurrentFont()), 33);
		} else {
			String numStr = StringManager.getMessage(MessageID.UI_CHAPTERNO_STD, getLevelChapterNumber(levelId));
			GameRuntime.drawText(numStr, 0, numStr.length(), x, bottomY - GameRuntime.getFontHeight(GameRuntime.getCurrentFont()), 33);
		}
		String levelName = StringManager.getMessage(LEVEL_NAME_MESSAGE_IDS[levelId]);
		GameRuntime.drawText(levelName, 0, levelName.length(), x, bottomY, Graphics.BOTTOM | Graphics.HCENTER);
	}

	/* renamed from: a */
	private static void drawBookFrame(int xpos, int ypos, Graphics graphics) {
		GameRuntime.drawImageRes(xpos, ypos, 331);
		int yparam = GameRuntime.getImageAnimParamEx(331, 0);
		int xEnd = yparam >> 16;
		short yEnd = (short) (yparam & 0xFFFF);
		int xparam = GameRuntime.getImageAnimParamEx(331, 1);
		int xStart = xparam >> 16;
		short yStart = (short) (xparam & 0xFFFF);
		graphics.setColor(0xFBF7E3);
		graphics.fillRect(xpos + xStart, ypos + yStart, xEnd - xStart, yEnd - yStart);
		graphics.setColor(0);
		graphics.fillRect((xpos + xStart) - 2, ypos + yStart, 4, yEnd - yStart);
	}

	/* renamed from: a */
	private static void setBGColor(int rgb, Graphics graphics) {
		if ((isColorsAreStolen && !isFlashToOtherColorMode) || (!isColorsAreStolen && isFlashToOtherColorMode)) {
			int red = (rgb >> 16) & 255;
			int green = (rgb >> 8) & 255;
			int blue = rgb & 255;
			rgb = (((green + blue) >> 1) << 16) + (((blue + red) >> 1) << 8) + ((red + green) >> 1);
		}
		graphics.setColor(rgb);
	}

	/* renamed from: a */
	private static void updateLevelStats(int levelId, short eggCount, short clearTime, short score) {
		int saveDataOffset = levelId << 2;
		if (eggCount > levelSaveData[saveDataOffset]) {
			levelSaveData[saveDataOffset] = eggCount;
		}
		if (clearTime < levelSaveData[saveDataOffset + 1]) {
			levelSaveData[saveDataOffset + 1] = clearTime;
		}
		if (score > levelSaveData[saveDataOffset + 2]) {
			levelSaveData[saveDataOffset + 2] = score;
		}
		if (score > levelSaveData[saveDataOffset + 3]) {
			levelSaveData[saveDataOffset + 3] = score;
		}
	}

	/* renamed from: a */
	private static void drawTranslucentSoftkeyBar(DirectGraphics directGraphics) {
		int skbHeight = GameRuntime.getSoftkeyBarHeight();
		int width = GameRuntime.currentWidth;
		int height = GameRuntime.currentHeight - skbHeight;
		xluSoftkeyBarXs[0] = 0;
		xluSoftkeyBarYs[0] = height;
		xluSoftkeyBarXs[1] = 0 + width;
		xluSoftkeyBarYs[1] = height;
		xluSoftkeyBarXs[2] = 0 + width;
		xluSoftkeyBarYs[2] = height + skbHeight;
		xluSoftkeyBarXs[3] = 0;
		xluSoftkeyBarYs[3] = skbHeight + height;
		directGraphics.fillPolygon(xluSoftkeyBarXs, 0, xluSoftkeyBarYs, 0, 4, 0x55000000);
	}

	/* renamed from: a */
	private static void updateLevelStartSoftkeyByUnlock(UILayout ui) {
		if (isLevelUnlocked(selectedLevelId)) {
			ui.changeSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_SELECT), 0);
		} else {
			ui.changeSoftkey(GameRuntime.SOFTKEY_CENTER, (String) null, 0);
		}
	}

	private static void cycleLevelSelectLeft(UILayout layout, boolean z) {
		if (selectedLevelId != 0) {
			if (f315l <= selectedLevelId) {
				z = false;
			}
			f315l = selectedLevelId;
			selectedLevelId--;
			if (f319m == 650 || z) {
				f319m = 0;
			}
			f323n = 650;
			updateLevelStartSoftkeyByUnlock(layout);
		}
	}

	/* renamed from: a */
	private static void cycleLevelSelectRight(UILayout layout, boolean z) {
		if (selectedLevelId != 14) {
			boolean z2 = f315l < selectedLevelId;
			f315l = selectedLevelId;
			selectedLevelId++;
			if (f319m == 0 || z2) {
				f319m = 650;
			}
			f323n = 0;
			if (z) {
				updateLevelStartSoftkeyByUnlock(layout);
			}
		}
	}

	/* renamed from: a */
	public static void drawSoftkeyUI(String str, int type, int xpos, int ypos, int flags) {
		if (type == 1) {
			GameRuntime.drawImageResAnchored(xpos, ypos, (flags | Graphics.TOP) == Graphics.TOP ? 151 : 150, flags);
		} else {
			GameRuntime.setTextStyle(-3, 3);
			GameRuntime.setTextColor(0, 0xFF7800);
			GameRuntime.setTextColor(1, 0);
			GameRuntime.drawText(str, 0, str.length(), xpos, ypos, flags);
		}
	}

	/* renamed from: a */
	private static void clearUIBackground(Graphics graphics) {
		graphics.setColor(0x3F1A01);
		graphics.fillRect(0, 0, GameRuntime.currentWidth >> 1, GameRuntime.currentHeight);
		graphics.setColor(0x5E2601);
		graphics.fillRect(GameRuntime.currentWidth >> 1, 0, GameRuntime.currentWidth >> 1, GameRuntime.currentHeight);
		GameRuntime.drawImageRes(0, 0, 4);
		GameRuntime.drawImageRes(0, 0, 74);
		GameRuntime.drawImageRes(0, 0, 75);
		GameRuntime.drawImageRes(0, GameRuntime.currentHeight, 3);
	}

	/* renamed from: a */
	private static void drawBGParallax(short[] imageIDs, int xStep, int i2, int x, int xRange, int y, int yRange, int count, int stripeFillColor, Graphics graphics) {
		paralaxXOffsets[0] = 0;
		int i9 = 0;
		if (yRange != 0) {
			i9 = mRNG.nextInt() % yRange;
		}
		parallaxYOffsets[0] = i9 + y;
		for (int i = 1; i < count + 1; i++) {
			int i11 = 0;
			if (xRange != 0) {
				i11 = Math.abs(mRNG.nextInt() % xRange);
			}
			paralaxXOffsets[i] = i11 + paralaxXOffsets[i - 1] + x;
			int i12 = 0;
			if (yRange != 0) {
				i12 = mRNG.nextInt() % yRange;
			}
			parallaxYOffsets[i] = i12 + y;
			parallaxImageIndices[i] = Math.abs(mRNG.nextInt() % imageIDs.length);
		}
		int i13 = paralaxXOffsets[count];
		int baseX = i13 - (((((((GameObject.cameraMatrix.translationX >> 16) + 33000) * GameObject.screenSpaceMatrix.m00) >> 16) * xStep) / i2) % i13);
		int baseY = GameRuntime.currentHeight + ((((((GameObject.cameraMatrix.translationY - f240F) >> 16) * GameObject.screenSpaceMatrix.m00) >> 16) * xStep) / i2);
		for (int i = 0; i < count; i++) {
			if (i < 2) {
				GameRuntime.drawImageRes(paralaxXOffsets[i] + baseX, parallaxYOffsets[i] + baseY, imageIDs[parallaxImageIndices[i]]);
			}
			if (i > 2) {
				GameRuntime.drawImageRes((paralaxXOffsets[i] + baseX) - (i13 << 1), parallaxYOffsets[i] + baseY, imageIDs[parallaxImageIndices[i]]);
			}
			GameRuntime.drawImageRes((paralaxXOffsets[i] + baseX) - i13, parallaxYOffsets[i] + baseY, imageIDs[parallaxImageIndices[i]]);
		}
		if (stripeFillColor != -1) {
			setBGColor(stripeFillColor, graphics);
			graphics.fillRect(0, parallaxYOffsets[0] + baseY, GameRuntime.currentWidth, GameRuntime.currentHeight - (baseY + parallaxYOffsets[0]));
		}
	}

	/* renamed from: a */
	private static boolean checkSuperBounceUnlocked() {
		for (int i = 0; i < 15; i++) {
			if (getLevelEggCount(i) < LEVEL_EGG_TROPHY_REQUIREMENTS[i * 3] || getLevelClearTime(i) > LEVEL_TIMER_TROPHY_REQUIREMENTS[i * 3]) {
				return false;
			}
		}
		return true;
	}

	/* renamed from: a */
	public static boolean wasLevelBeaten(int levelId) {
		int clearTime = getLevelClearTime(levelId);
		return clearTime > 0 && clearTime < 9999;
	}

	/* renamed from: a */
	public static boolean drawUIGraphics(UILayout ui, int type, int xpos, int ypos, int width, int height) {
		GameRuntime.setBacklight(true);
		Graphics grp = GameRuntime.getGraphicsObj();
		int a2 = GameRuntime.updateDelta * GameRuntime.getUpdatesPerDraw();
		DirectGraphics directGraphics = DirectUtils.getDirectGraphics(grp);
		if ((ui.currentStage == 34 || ui.currentStage == 25 || ui.currentStage == 28 || ui.currentStage == 29 || ui.currentStage == 30) && type == 1) {
			int[] xpoints = GeometryObject.TEMP_QUAD_XS;
			int[] ypoints = GeometryObject.TEMP_QUAD_YS;
			xpoints[0] = xpos;
			ypoints[0] = ypos;
			xpoints[1] = xpos + width;
			ypoints[1] = ypos;
			xpoints[2] = xpos + width;
			ypoints[2] = ypos + height;
			xpoints[3] = xpos;
			ypoints[3] = ypos + height;
			directGraphics.fillPolygon(xpoints, 0, ypoints, 0, 4, 0x55000000);
			GameRuntime.drawImageRes(xpos, ypos, 311);
			GameRuntime.drawImageRes(xpos + width, ypos, 312);
			GameRuntime.drawImageRes(xpos, ypos + height, 309);
			GameRuntime.drawImageRes(xpos + width, ypos + height, 310);
			drawTranslucentSoftkeyBar(directGraphics);
			return false;
		} else if (ui.currentStage == 18) {
			if (type == 1) {
				clearUIBackground(grp);
				int i6 = GameRuntime.currentWidth >> 1;
				int i7 = GameRuntime.currentHeight >> 1;
				int b = ((short) GameRuntime.getImageAnimParamEx(331, 2)) + i7;
				int b2 = ((short) GameRuntime.getImageAnimParamEx(331, 3)) + i7;
				drawBookFrame(i6, i7, grp);
				if (selectedLevelId != 0) {
					GameRuntime.drawImageResAnchored(3, i7, 326, 6);
				}
				if (selectedLevelId != 14) {
					GameRuntime.drawImageResAnchored(GameRuntime.currentWidth - 3, i7, 2, 10);
				}
				int i8 = 0;
				int i9 = 0;
				if (f319m < f323n) {
					int i10 = f319m + a2;
					f319m = i10;
					if (i10 > f323n) {
						f319m = f323n;
					}
					i8 = selectedLevelId;
					i9 = f315l;
				} else if (f319m > f323n) {
					int i11 = f319m - a2;
					f319m = i11;
					if (i11 < f323n) {
						f319m = f323n;
					}
					i8 = f315l;
					i9 = selectedLevelId;
				}
				if (f319m > 400 && f319m < 650) {
					drawLevelSelectUI(i6, i7, i8, b, b2);
					GameRuntime.drawAnimatedImageRes(i6, i7, 442, ((f319m - 400) << 1) / 250);
				} else if (f319m > 400 || f319m <= 0) {
					drawLevelSelectUI(i6, i7, selectedLevelId, b, b2);
				} else {
					int i12 = ((i6 - 119) - 239) + 22;
					int i13 = (i6 + 120) - 30;
					int i14 = (i6 - 119) + 22;
					int i15 = i12 + ((((i13 - 25) - i12) * f319m) / 400);
					int i16 = (((i13 - i14) * f319m) / 400) + i14;
					int i17 = (i7 - 158) - 3;
					grp.setClip(0, 0, i15 + 3, GameRuntime.currentHeight);
					drawLevelSelectUI(i6, i7, i8, b, b2);
					grp.setClip(i16 - 2, 0, (GameRuntime.currentWidth - i16) + 2, GameRuntime.currentHeight);
					drawLevelSelectUI(i6, i7, i9, b, b2);
					grp.setClip(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
					GameRuntime.drawImageRes(i15, i17, 377);
					grp.setColor(15394508);
					int i18 = ((i16 - i15) - 12) - 13;
					grp.fillRect(i15 + 12, i17, i18, 295);
					grp.setColor(0);
					grp.fillRect(i15 + 12, i17, i18, 1);
					grp.fillRect(i15 + 12, ((i17 + 307) - 1) - 12, i18, 1);
					GameRuntime.drawImageRes(i18 + i15 + 12, i17, 378);
					GameRuntime.drawImageRes(i6, i7, 376);
				}
				GameRuntime.setTextStyle(-3, 3);
				GameRuntime.setTextColor(0, 16742400);
				GameRuntime.setTextColor(1, 0);
				GameRuntime.drawImageRes(9, 9, 102);
				String stringBuffer = getTotalEggCount() + "/450";
				GameRuntime.drawText(stringBuffer, 0, stringBuffer.length(), 41, 10, 20);
				drawTranslucentSoftkeyBar(directGraphics);
			}
			return false;
		} else if (ui.currentStage == 25 || ui.currentStage == 28 || ui.currentStage == 29 || ui.currentStage == 30 || ui.currentStage == 15 || ui.currentStage == 34 || type != 1) {
			if (type == 1) {
				drawTranslucentSoftkeyBar(directGraphics);
			}
			if (type == 4 || type == 2 || type == 9) {
				return false;
			}
			if (ui.currentStage == 25 || ui.currentStage == 28 || ui.currentStage == 29 || ui.currentStage == 30 || ui.currentStage == 34 || type != 10) {
				return true;
			}
			grp.setClip(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
			int selArrowDisp = (SIN_COS_TABLE[(int) ((System.currentTimeMillis() >> 1) % 360)] * 5) / 360;
			GameRuntime.drawImageResAnchored(
					((xpos - 5) - 4) + selArrowDisp,
					(height >> 1) + ypos,
					2,
					Graphics.RIGHT | Graphics.VCENTER
			); //selection arrow L
			GameRuntime.drawImageResAnchored(
					(((xpos + width) + 5) + 4) - selArrowDisp,
					(height >> 1) + ypos,
					326,
					Graphics.LEFT | Graphics.VCENTER
			); //selection arrow R
			return false;
		} else {
			clearUIBackground(grp);
			int i20 = GameRuntime.currentWidth >> 1;
			int i21 = GameRuntime.currentHeight >> 1;
			if (ui.currentStage == 17) {
				int b3 = GameRuntime.getImageAnimParamEx(332, 0);
				int i22 = b3 >> 16;
				short s = (short) b3;
				int b4 = GameRuntime.getImageAnimParamEx(332, 1);
				int i23 = b4 >> 16;
				int i24 = i20 - 117;
				int i25 = i21 - 157;
				int i26 = (i20 + i23) - i24;
				grp.setColor(0x644330);
				grp.fillRect(i20 + i22, i21 + s, i23 - i22, ((short) b4) - s);
				GameRuntime.drawImageRes(i20, i21, 332);
				grp.setColor(0x55270F);
				grp.drawRect(i24 + 2, i25 + 2, i26 - 4, 296);
				grp.drawRect(i24 + 3, i25 + 3, i26 - 6, 294);
				grp.setColor(0x371909);
				grp.drawRect(i24, i25, i26, 300);
				grp.drawRect(i24 + 1, i25 + 1, i26 - 2, 298);
				GameRuntime.drawImageResTransformed(((i20 + i26) - 117) + 2, (i21 - 157) - 2, 12, Graphics.TOP | Graphics.RIGHT, Sprite.TRANS_ROT270);
				GameRuntime.drawImageResAnchored(((i20 + i26) - 117) + 2, ((i21 + 300) - 157) + 2, 12, Graphics.BOTTOM | Graphics.RIGHT);
				GameRuntime.drawImageResAnchored((i20 - 117) - 5, i21 - 75, 15, Graphics.LEFT | Graphics.VCENTER);
				GameRuntime.drawImageResAnchored((i20 - 117) - 5, i21 + 75, 15, Graphics.LEFT | Graphics.VCENTER);
			} else {
				drawBookFrame(i20, i21, grp);
			}
			drawTranslucentSoftkeyBar(directGraphics);
			return false;
		}
	}

	/* renamed from: b */
	public static int getSoftkeyBarSize() {
		return GameRuntime.getFontHeight(-3) + 4;
	}

	/* renamed from: b */
	public static int getLevelType(int levelId) {
		int result = 2;
		if (levelId <= LevelID.TUNNEL_OF_TREASURES) {
			result = 1;
		}
		if (levelId <= LevelID.SECRET_STALKWAY) {
			return 0;
		}
		return result;
	}

	/* renamed from: b */
	private static String formatGameTimer(int seconds) {
		int mm = seconds / 60;
		int ss = seconds % 60;
		return ss < 10 ? mm + ":0" + ss : mm + ":" + ss;
	}

	/* renamed from: b */
	private void setIngameHID() {
		this.drawUI = null;
		GameRuntime.resetSoftkeys();
		GameRuntime.setSoftkey(GameRuntime.SOFTKEY_RIGHT, "", 1);
		GameRuntime.initHID(2);
		GameRuntime.resetHID();
	}

	/* renamed from: b */
	private void drawLoadingBar(Graphics graphics) {
		if (hasLoadingProgressBar) {
			graphics.setColor(0x703005);
			graphics.fillRect(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
			graphics.setColor(0);
			graphics.fillRect((GameRuntime.currentWidth - 60) / 2, (GameRuntime.currentHeight - 10) / 2, 60, 10);
			graphics.setColor(0x471D00);
			graphics.fillRect(((GameRuntime.currentWidth - 60) / 2) + 1, ((GameRuntime.currentHeight - 10) / 2) + 1, 58, 8);
			graphics.setColor(0xAEE13C);
			graphics.fillRect(((GameRuntime.currentWidth - 60) / 2) + 1, ((GameRuntime.currentHeight - 10) / 2) + 1, ((this.loadingProgressBar * 60) / 20) - 2, 8);
			this.loadingProgressBar++;
			if (this.loadingProgressBar > 20) {
				this.loadingProgressBar = 0;
				return;
			}
			return;
		}
		graphics.setColor(0xFFFFFF);
		graphics.fillRect(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
	}

	/* renamed from: b */
	private static boolean isLevelUnlocked(int levelId) {
		return getLevelClearTime(levelId) > 0;
	}

	/* renamed from: c */
	private static int getTotalEggCount() {
		int totalEggs = 0;
		for (int levelIdx = 0; levelIdx < LevelID.LEVEL_IDX_MAX; levelIdx++) {
			totalEggs += getLevelEggCount(levelIdx);
		}
		return totalEggs;
	}

	private static void deserializeSaveData(byte[] save) {
		for (int saveDataInIdx = 0, saveDataOutIdx = 0; saveDataOutIdx < levelSaveData.length; saveDataOutIdx++, saveDataInIdx += 2) {
			levelSaveData[saveDataOutIdx] = GameObject.readShort(save, saveDataInIdx);
		}
	}

	private static void clearSaveData() {
		for (int i = 0; i < levelSaveData.length; i++) {
			levelSaveData[i] = 0;
		}
	}

	private static void setLevelEggCount(int levelId, int eggCount) {
		levelSaveData[levelId << 2] = (short) eggCount;
	}

	private static void setLevelClearTime(int levelId, int clearTime) {
		levelSaveData[(levelId << 2) + 1] = (short) clearTime;
	}

	private static int getLevelEggCount(int levelId) {
		return levelSaveData[levelId << 2];
	}

	private static int getLevelClearTime(int levelId) {
		return levelSaveData[(levelId << 2) + 1];
	}

	private static short getLevelLocalHighScore(int levelId) {
		return levelSaveData[(levelId << 2) + 2];
	}

	private static short getLevelGlobalHighScore(int levelId) {
		return levelSaveData[(levelId << 2) + 3];
	}

	/* renamed from: c */
	public static int getStolenColorIfApplicable(int i) {
		//10 - false
		//01 - false
		//11 - true
		//00 - true
		if ((isColorsAreStolen && isFlashToOtherColorMode) || (!isColorsAreStolen && !isFlashToOtherColorMode)) {
			return i;
		}
		int i2 = (i >> 16) & 255;
		int i3 = (i >> 8) & 255;
		int i4 = i & 255;
		return ((i >>> 24) << 24) + (((i3 + i4) >> 1) << 16) + (((i4 + i2) >> 1) << 8) + ((i2 + i3) >> 1);
	}

	/* renamed from: c */
	private void popFieldMessage() {
		if (!isFieldMessageShowing && fieldMessagePointer > 0) {
			setUI(34);
			isBlockingEvent = true;
			isFieldMessageShowing = true;
			for (int i = 0; i < 4; i++) {
				fieldMessageQueue[i] = fieldMessageQueue[i + 1];
			}
			fieldMessagePointer--;
		}
	}

	/* renamed from: c */
	private static boolean isBonusLevel(int i) {
		for (int i2 = 0; i2 < BONUS_LEVEL_INFO.length; i2 += 2) {
			if (i == BONUS_LEVEL_INFO[i2]) {
				return true;
			}
		}
		return false;
	}

	/* renamed from: d */
	private static int getLevelMusicID() {
		switch (currentLevel) {
			case LevelID.SECRET_STALKWAY:
			case LevelID.TUNNEL_OF_TREASURES:
			case LevelID.FANTASTIC_FAIR:
				return SoundID.LEVEL_BONUS;
			case LevelID.BUMPY_CRACKS:
			case LevelID.TRAPPED_IN_MACHINE:
			case LevelID.FINAL_RIDE:
				return SoundID.LEVEL_BOSS;
		}
		switch (getLevelType(currentLevel)) {
			case 0:
				return SoundID.LEVEL_ACT_1;
			case 1:
				return SoundID.LEVEL_ACT_2;
			default:
				return SoundID.LEVEL_ACT_3;
		}
	}

	/* renamed from: d */
	private static int getCollectionChallengeRank(int levelId) {
		int collectedEggs = getLevelEggCount(levelId);
		if (collectedEggs >= LEVEL_EGG_TROPHY_REQUIREMENTS[levelId * 3]) {
			return 2;
		}
		if (collectedEggs >= LEVEL_EGG_TROPHY_REQUIREMENTS[(levelId * 3) + 1]) {
			return 1;
		}
		if (collectedEggs >= LEVEL_EGG_TROPHY_REQUIREMENTS[(levelId * 3) + 2]) {
			return 0;
		}
		return -1;
	}

	/* renamed from: d */
	private static void serializeSaveData() {
		byte[] savedata = new byte[(levelSaveData.length << 1)];
		for (int i = 0; i < levelSaveData.length; i++) {
			savedata[i << 1] = (byte) (levelSaveData[i] >> 8);
			savedata[(i << 1) + 1] = (byte) levelSaveData[i];
		}
		GameRuntime.saveToRecordStore("game", savedata);
	}

	/* renamed from: e */
	private static int getTimerChallengeRank(int levelId) {
		int clearTime = getLevelClearTime(levelId);
		if (clearTime <= LEVEL_TIMER_TROPHY_REQUIREMENTS[levelId * 3]) {
			return 2;
		}
		if (clearTime <= LEVEL_TIMER_TROPHY_REQUIREMENTS[(levelId * 3) + 1]) {
			return 1;
		}
		if (clearTime <= LEVEL_TIMER_TROPHY_REQUIREMENTS[(levelId * 3) + 2]) {
			return 0;
		}
		return -1;
	}

	/* renamed from: e */
	private void updateLoadingScreen() {
		if (this.curSplashId + 1 >= SPLASH_SCREEN_LAYOUT_RESIDS.length) {
			GameRuntime.startLoadScene(36);
		} else if (GameRuntime.isResourceLoadDone(SPLASH_SCREEN_LAYOUT_RESIDS[this.curSplashId + 1])) {
			hasLoadingProgressBar = true;
			this.curSplashId++;
			this.splashScreenStartTime = System.currentTimeMillis();
			if (this.curSplashId - 1 > -1) {
				GameRuntime.unloadResource(SPLASH_SCREEN_LAYOUT_RESIDS[this.curSplashId - 1]);
			}
		}
	}

	/* renamed from: e */
	private static void unlockLevel(int levelId) {
		if (getLevelClearTime(levelId) == 0) {
			setLevelClearTime(levelId, 9999);
		}
	}

	/* renamed from: f */
	private static void freeIngameData() {
		ballFramebuffer = null;
		ballGraphics = null;
		ballFramebufferRGB = null;
		f266b = null;
		f265b = null;
		f270b = null;
		GeometryObject.TEMP_QUAD_XS = null;
		GeometryObject.TEMP_QUAD_YS = null;
		rootLevelObj = null;
		GameObject.cameraTarget = null;
		bounceObj = null;
		events = null;
		currentCannon = null;
		parallaxImagesRegColors = null;
		parallaxImagesStolenColors = null;
		GameRuntime.unloadResource(2);
		GameRuntime.unloadResource(1);
		GameRuntime.unloadResource(6);
		GameRuntime.unloadResource(5);
		GameRuntime.unloadResource(24);
		GameRuntime.unloadResource(0);
		GameRuntime.unloadResource(3);
		GameRuntime.unloadResource(26);
		GameRuntime.unloadResource(20);
		GameRuntime.unloadResource(19);
		GameRuntime.unloadResource(16);
		GameRuntime.unloadResource(25);
		GameRuntime.unloadResource(22);
		GameRuntime.unloadResource(27);
		GameRuntime.unloadResource(21);
		GameRuntime.unloadResource(23);
		int act = getLevelType(currentLevel);
		if (act == 0) {
			GameRuntime.unloadResource(12);
			GameRuntime.unloadResource(17);
			GameRuntime.unloadResource(18);
		}
		if (act == 1) {
			GameRuntime.unloadResource(11);
			GameRuntime.unloadResource(17);
			GameRuntime.unloadResource(18);
		}
		if (act == 2) {
			GameRuntime.unloadResource(8);
			GameRuntime.unloadResource(4);
		}
		GameRuntime.loadResource(10);
		GameRuntime.loadResource(13);
	}

	/* renamed from: f */
	private static void debugLevelUnlock(int levelId) {
		int status = getLevelClearTime(levelId);
		if (status == 0 || status == 9999) {
			setLevelClearTime(levelId, 300);
		}
	}

	/* renamed from: g */
	private static void initStolenColorData() {
		stolenColorsAnimationCountdown = 0;
		stolenColorsFlashCountdown = 0;
		isFlashToOtherColorMode = false;
		isColorsAreStolen = false;
		try {
			parallaxImagesRegColors = new Image[ALL_PARALLAX_IMAGE_IDS.length];
			parallaxImagesStolenColors = new Image[ALL_PARALLAX_IMAGE_IDS.length];
			for (int i = 0; i < ALL_PARALLAX_IMAGE_IDS.length; i++) {
				if (GameRuntime.getImageResource(ALL_PARALLAX_IMAGE_IDS[i]) != null) {
					parallaxImagesRegColors[i] = GameRuntime.getImageResource(ALL_PARALLAX_IMAGE_IDS[i]);
					parallaxImagesStolenColors[i] = Image.createImage(parallaxImagesRegColors[i]);
					int[] stolenRGB = new int[(parallaxImagesStolenColors[i].getWidth() * parallaxImagesStolenColors[i].getHeight())];
					parallaxImagesStolenColors[i].getRGB(
							stolenRGB,
							0,
							parallaxImagesStolenColors[i].getWidth(),
							0,
							0,
							parallaxImagesStolenColors[i].getWidth(),
							parallaxImagesStolenColors[i].getHeight()
					);
					for (int rgbIdx = 0; rgbIdx < stolenRGB.length; rgbIdx++) {
						int r = (stolenRGB[rgbIdx] >> 16) & 255;
						int g = (stolenRGB[rgbIdx] >> 8) & 255;
						int b = stolenRGB[rgbIdx] & 255;
						stolenRGB[rgbIdx] = ((stolenRGB[rgbIdx] >>> 24) << 24) + (((g + b) >> 1) << 16) + (((b + r) >> 1) << 8) + ((r + g) >> 1);
					}
					parallaxImagesStolenColors[i] = Image.createRGBImage(stolenRGB, parallaxImagesStolenColors[i].getWidth(), parallaxImagesStolenColors[i].getHeight(), true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* renamed from: g */
	private void setUI(int uiID) {
		System.out.println("Bounce setUI " + uiID);
		this.ui.clear();
		this.ui.disableSoftkey(0);
		this.ui.disableSoftkey(1);
		this.ui.layoutAttributes = null;
		this.ui.elemDefaultAttributes = null;
		this.ui.currentStage = uiID;
		this.ui.setElemDefaultAttribute(UIElement.FONT, -2);
		this.ui.setAttribute(UILayout.FONT, -2);
		switch (uiID) {
			case 10: //high scores list
				this.ui.loadFromResource(36);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f341t);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - (f344u << 1)) + 4);
				this.ui.setAttribute(UILayout.OFFSET_LEFT, f344u - 2);

				//HD stuff
				//ui.setAttribute(UILayout.SOFTKEY_BAR, 64);
				//ui.setAttribute(UILayout.FIXED_HEIGHT, 300);
				this.ui.setElemDefaultAttribute(3, 0);
				this.ui.setElemDefaultAttribute(2, 32);
				this.ui.setAttribute(UILayout.BLOCK_INCREMENT, GameRuntime.getFontHeight(-3) << 1);
				this.ui.setTitle(StringManager.getMessage(MessageID.UI_HIGH_SCORE), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_BACK), 0, 17, true);
				boolean hasAnyHighScore = false;
				for (int levelIdx = 0; levelIdx < LevelID.LEVEL_IDX_MAX; levelIdx++) {
					if (getLevelGlobalHighScore(levelIdx) > 0) {
						hasAnyHighScore = true;
					}
				}
				if (hasAnyHighScore) {
					for (int levelIdx = 0; levelIdx < LevelID.LEVEL_IDX_MAX; levelIdx++) {
						int highScore = Math.max(0, getLevelGlobalHighScore(levelIdx));
						String chapterNoStr;
						if (isBonusLevel(levelIdx)) {
							chapterNoStr = StringManager.getMessage(MessageID.UI_CHAPTERNO_BONUS, getLevelChapterNumber(levelIdx));
						} else {
							chapterNoStr = StringManager.getMessage(MessageID.UI_CHAPTERNO_STD, getLevelChapterNumber(levelIdx));
						}
						int separatorImageId = 89;
						if (levelIdx == 0) {
							separatorImageId = -1;
						}
						this.ui.addElement(new UIElement(chapterNoStr + "\n" + highScore, separatorImageId, this.ui, -1));
					}
					break;
				} else {
					this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.EMPTY), -1, this.ui, -1));
					break;
				}
			case 17: //main menu
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -2); //font
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setElemDefaultAttribute(UIElement.AUTO_WIDTH, 256);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f332q);
				this.ui.setAttribute(5, 128);

				//for center
				//ui.setAttribute(UILayout.VERTICAL_SPACING, 10);
				//ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, 200);
				//this.ui.setAttribute(UILayout.SOFTKEY_BAR, 64);
				//this.ui.setAttribute(3, 32);
				this.ui.setElemDefaultAttribute(UIElement.FONT_TEXT_COLOR_SELECTED, 0xFF7800);
				this.ui.setTitle("", -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_SELECT), 0, -2, true);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_LEAVE), 0, 9, false);
				if (isLevelUnlocked(1)) {
					this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.UI_CONTINUE), -1, this.ui, 18));
					this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.UI_NEW_GAME), -1, this.ui, 20));
				} else {
					this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.UI_NEW_GAME), -1, this.ui, 18));
					selectedLevelId = 0;
				}
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.UI_HIGH_SCORE), -1, this.ui, 10));
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.UI_GUIDE), -1, this.ui, 22));
				this.ui.setSelectedOption(lastMainMenuOption);
				break;
			case 18: //level select
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_SELECT), 0, 7, true);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_BACK), 0, 17, true);
				updateLevelStartSoftkeyByUnlock(this.ui);
				break;
			case 20: //start new game
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setTitle(StringManager.getMessage(MessageID.DIALOG_NEW_GAME), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_YES), 0, 11, false);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_NO), 0, 17, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.GAME_PROGRESS_WILL_BE_LOST), -1, this.ui, -1));
				break;
			case 22: //guide
				this.ui.loadFromResource(36);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - (f344u << 1)) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f341t);
				this.ui.setAttribute(UILayout.OFFSET_LEFT, f344u - 2);
				this.ui.setElemDefaultAttribute(3, 0);
				this.ui.setElemDefaultAttribute(2, 32);
				this.ui.setAttribute(UILayout.BLOCK_INCREMENT, GameRuntime.getFontHeight(-3) << 1);
				this.ui.setTitle(StringManager.getMessage(MessageID.UI_GUIDE), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_BACK), 0, 17, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(12), -1, this.ui, -1));
				this.ui.addElement(new UIElement(StringManager.getMessage(13), 102, this.ui, -1));
				this.ui.addElement(new UIElement(StringManager.getMessage(11), -1, this.ui, -1));
				this.ui.addElement(new UIElement(StringManager.getMessage(14), 371, this.ui, -1));
				this.ui.addElement(new UIElement(StringManager.getMessage(15), 372, this.ui, -1));
				break;
			case 24: //quit game
				this.ui.setTitle(StringManager.getMessage(80), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(49), 0, 9, false);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(45), 0, 17, true);
				break;
			case 25: //pause menu
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setElemDefaultAttribute(UIElement.AUTO_WIDTH, 256);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - f347v) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentWidth - f347v);
				this.ui.setAttribute(4, 64);
				this.ui.setAttribute(3, 32);
				this.ui.setAttribute(5, 128);
				this.ui.setElemDefaultAttribute(UIElement.FONT_TEXT_COLOR_SELECTED, 0xFF7800);
				this.ui.setAttribute(2, 0);
				this.ui.setTitle(StringManager.getMessage(MessageID.DIALOG_PAUSE_MENU), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_SELECT), 0, -2, true);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_QUIT), 0, 30, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(38), -1, this.ui, 8));
				this.ui.addElement(new UIElement(StringManager.getMessage(37), -1, this.ui, 28));
				this.ui.addElement(new UIElement(StringManager.getMessage(39), -1, this.ui, 29));
				this.ui.setSelectedOption(lastMainMenuOption);
				break;
			case 28: //confirm restart level
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setElemDefaultAttribute(UIElement.AUTO_WIDTH, 256);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - f347v) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f347v);
				this.ui.setAttribute(4, 64);
				this.ui.setAttribute(3, 32);
				this.ui.setAttribute(2, 0);
				this.ui.setTitle(StringManager.getMessage(MessageID.DIALOG_RESTART_LEVEL), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(49), 0, 37, false);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(45), 0, 25, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(3), -1, this.ui, -1));
				break;
			case 29: //confirm return to level select
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				mainMenuReturnUI = 18;
				this.ui.setElemDefaultAttribute(UIElement.AUTO_WIDTH, 256);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - f347v) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f347v);
				this.ui.setAttribute(4, 64);
				this.ui.setAttribute(3, 32);
				this.ui.setAttribute(2, 0);
				this.ui.setTitle(StringManager.getMessage(87), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(49), 0, 5, false);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(45), 0, 25, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(3), -1, this.ui, -1));
				break;
			case 30: //confirm quit level
				this.ui.loadFromResource(37);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				mainMenuReturnUI = 17;
				this.ui.setElemDefaultAttribute(UIElement.AUTO_WIDTH, 256);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - f347v) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f347v);
				this.ui.setAttribute(4, 64);
				this.ui.setAttribute(3, 32);
				this.ui.setAttribute(2, 0);
				this.ui.setTitle(StringManager.getMessage(MessageID.DIALOG_QUIT_GAME), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_YES), 0, 5, false);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_RIGHT, StringManager.getMessage(MessageID.UI_NO), 0, 25, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.LEVEL_PROGRESS_WILL_BE_LOST), -1, this.ui, -1));
				break;
			case 31: //chapter complete
				this.ui.loadFromResource(36);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - (f344u << 1)) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f341t);
				this.ui.setAttribute(UILayout.OFFSET_LEFT, f344u - 2);
				this.ui.setTitle(StringManager.getMessage(MessageID.CHAPTER_COMPLETE), -1, 1);
				if (this.wasSuperBounceJustUnlocked) {
					this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(46), 0, 33, true);
				} else if (this.wasFinalLevelJustBeaten) {
					this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(46), 0, 32, true);
				} else {
					this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(46), 0, 18, true);
				}
				this.ui.addElement(new UIElement(StringManager.getMessage(42, calcScore), -1, this.ui, -1));
				String eggsCollectedStr = eggCount + "/" + bonusLevelEggLimit;
				UIElement eggsCollectedUI = new UIElement(eggsCollectedStr, 102, this.ui, -1);
				if (isTextRightToLeft) {
					eggsCollectedUI.setAttribute(2, 16);
					eggsCollectedUI.setText(eggsCollectedStr, 102);
				}
				this.ui.addElement(eggsCollectedUI);
				String timerStr = formatGameTimer(levelTimer / 1000);
				UIElement timerUI = new UIElement(timerStr, 103, this.ui, -1);
				if (isTextRightToLeft) {
					timerUI.setAttribute(2, 16);
					timerUI.setText(timerStr, 103);
				}
				this.ui.addElement(timerUI);
				if (this.highScoreBeaten) {
					UIElement fVar3 = new UIElement(StringManager.getMessage(40), -1, this.ui, -1);
					fVar3.setAttribute(1, 8);
					fVar3.setText(StringManager.getMessage(40), -1);
					this.ui.addElement(fVar3);
				}
				if (wasLevelBeaten(LevelID.FINAL_RIDE) && !this.wasFinalLevelJustBeaten && !isBonusLevel(currentLevel)) {
					boolean z2 = false;
					if (this.timerChallengeTrophy > -1) {
						short timerTrophyImageId = TROPHY_IMAGE_IDS[this.timerChallengeTrophy];
						z2 = true;
						UIElement fVar4 = new UIElement(StringManager.getMessage(MessageID.TIMER_CHALLENGE), timerTrophyImageId, this.ui, -1);
						fVar4.setAttribute(2, 16);
						fVar4.setAttribute(3, 64);
						fVar4.setAttribute(1, 8);
						fVar4.setText(StringManager.getMessage(8), timerTrophyImageId);
						this.ui.addElement(fVar4);
					}
					if (this.collectionChallengeTrophy > -1) {
						short collectionTrophyImageId = TROPHY_IMAGE_IDS[this.collectionChallengeTrophy];
						z2 = true;
						UIElement fVar5 = new UIElement(StringManager.getMessage(MessageID.COLLECTION_CHALLENGE), collectionTrophyImageId, this.ui, -1);
						fVar5.setAttribute(2, 16);
						fVar5.setAttribute(3, 64);
						fVar5.setAttribute(1, 8);
						fVar5.setText(StringManager.getMessage(MessageID.COLLECTION_CHALLENGE), collectionTrophyImageId);
						this.ui.addElement(fVar5);
					}
					if (!z2) {
						UIElement fVar6 = new UIElement(StringManager.getMessage(41), -1, this.ui, -1);
						fVar6.setAttribute(1, 8);
						fVar6.setText(StringManager.getMessage(41), -1);
						this.ui.addElement(fVar6);
					}
				}
				this.wasFinalLevelJustBeaten = false;
				this.wasSuperBounceJustUnlocked = false;
				this.timerChallengeTrophy = -1;
				this.collectionChallengeTrophy = -1;
				this.highScoreBeaten = false;
				cycleLevelSelectRight(this.ui, false);
				break;
			case 32: //all levels beaten
				this.ui.loadFromResource(36);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f341t);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - (f344u << 1)) + 4);
				this.ui.setAttribute(UILayout.OFFSET_LEFT, f344u - 2);
				this.ui.setTitle(StringManager.getMessage(81), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_OK), 0, 18, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.ALL_LEVELS_BEATEN), -1, this.ui, -1));
				break;
			case 33: //all levels completed
				this.ui.loadFromResource(36);
				this.ui.setElemDefaultAttribute(UIElement.FONT, -3);
				this.ui.setAttribute(UILayout.FONT, -2);
				this.ui.setAttribute(UILayout.TITLE_PADDING_TOP, f335r);
				this.ui.setAttribute(UILayout.TITLE_PADDING_BOTTOM, f338s);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f341t);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - (f344u << 1)) + 4);
				this.ui.setAttribute(UILayout.OFFSET_LEFT, f344u - 2);
				this.ui.setTitle(StringManager.getMessage(82), -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_OK), 0, 18, true);
				this.ui.addElement(new UIElement(StringManager.getMessage(MessageID.ALL_LEVELS_COMPLETED), -1, this.ui, -1));
				break;
			case 34: //field message
				this.ui.setElemDefaultAttribute(UIElement.FONT, -1);
				this.ui.setAttribute(UILayout.FONT, -1);
				this.ui.setAttribute(UILayout.MARGIN_LEFT, 2);
				this.ui.setAttribute(UILayout.MARGIN_RIGHT, 2);
				this.ui.setAttribute(UILayout.FIXED_WIDTH, (GameRuntime.currentWidth - f347v) + 4);
				this.ui.setAttribute(UILayout.FIXED_HEIGHT, GameRuntime.currentHeight - f347v);
				this.ui.setAttribute(4, 64);
				this.ui.setAttribute(3, 32);
				this.ui.setAttribute(2, 0);
				this.ui.setTitle("", -1, 1);
				this.ui.setSoftkey(GameRuntime.SOFTKEY_CENTER, StringManager.getMessage(MessageID.UI_OK), 0, 35, true);
				if (reqReloadFieldMsg) {
					this.ui.addElement(new UIElement(lastFieldMsg, -1, this.ui, -1));
					reqReloadFieldMsg = false;
				} else {
					String fieldMsg;
					if (fieldMessageParam != null) {
						fieldMsg = StringManager.getMessage(fieldMessageQueue[0], fieldMessageParam);
						fieldMessageParam = null;
					} else {
						fieldMsg = StringManager.getMessage(fieldMessageQueue[0]);
					}
					if (fieldMsg.endsWith("\n\n")) {
						fieldMsg = fieldMsg.substring(0, fieldMsg.length() - 2);
					} else if (fieldMsg.startsWith("\n\n")) {
						fieldMsg = fieldMsg.substring(2, fieldMsg.length());
					}
					lastFieldMsg = fieldMsg;
					this.ui.addElement(new UIElement(fieldMsg, -1, this.ui, -1));
				}
				break;
		}
		this.drawUI = this.ui;
		this.ui.setupSoftkeys();
		GameRuntime.initHID(2);
		GameRuntime.resetHID();
	}

	/* renamed from: h */
	private static void resetParallaxStolenColors() {
		for (int i = 0; i < ALL_PARALLAX_IMAGE_IDS.length; i++) {
			if (!(GameRuntime.getImageResource(ALL_PARALLAX_IMAGE_IDS[i]) == null || parallaxImagesRegColors[i] == null)) {
				GameRuntime.replaceImageResource(ALL_PARALLAX_IMAGE_IDS[i], parallaxImagesRegColors[i]);
			}
		}
	}

	/* renamed from: i */
	private static void updateEvents() {
		if (isBlockingEvent) {
			EventObject.updateEvents(events);
			isBlockingEvent = false;
		} else {
			rootLevelObj.updateBBox();
			for (GameObject obj = rootLevelObj; obj != null; obj = obj.getNextNodeDescendToChildren(rootLevelObj)) {
				obj.updatePhysics();
			}
			EventObject.checkBounceEventTrigger(events, bounceObj);
			int stolenColorsBeforeScript = EventObject.eventVars[7];
			EventObject.updateEvents(events);
			if (EventObject.eventVars[7] != stolenColorsBeforeScript) {
				isFlashToOtherColorMode = false;
				stolenColorsAnimationCountdown = 2000;
				stolenColorsFlashCountdown = 0;
			}
			for (GameObject collider = rootLevelObj; collider != null; collider = collider.getNextNodeDescendToChildren(rootLevelObj)) {
				collider.checkCollisions(rootLevelObj);
			}
			if (reqCameraSnap) {
				GameObject.f174n = 0;
				GameObject.f175o = 0;
				GameObject.updateCamera(true);
				reqCameraSnap = false;
			} else {
				GameObject.updateCamera(false);
			}
		}
	}

	/* renamed from: j */
	private void levelEnded() {
		resetParallaxStolenColors();
		this.isLevelActive = false;
		GameRuntime.startLoadScene(5);
		mainMenuReturnUI = 31;
		setPlayerState(0);
	}

	/* renamed from: a */
	public final int update(int lastUpdateRes) {
		boolean bounceMoving;
		if (this.drawUI != null) {
			this.drawUI.setupSoftkeys();
			this.drawUI.updateTitleScroll();
		} else if (this.gameMainState == 4) {
			if (levelPaused) {
				setUI(25);
				GameRuntime.stopMusic();
			} else if (reqReloadFieldMsg) {
				setUI(34);
				isBlockingEvent = true;
				isFieldMessageShowing = true;
			} else {
				totalGameTime += GameRuntime.updateDelta;
				f255a = false;
				switch (getPlayerState()) {
					case 0:
						bounceObj.zCoord = 0;
						if (EventObject.eventVars[1] != CONTROLLER_FROZEN) {
							levelTimer += GameRuntime.updateDelta;
						}
						if (EventObject.eventVars[1] == CONTROLLER_NORMAL) {
							if (GameRuntime.checkButton(KeyCode.LEFT)) {
								bounceObj.moveLeft();
								bounceMoving = true;
							} else {
								bounceMoving = false;
							}
							if (GameRuntime.checkButton(KeyCode.RIGHT)) {
								bounceObj.moveRight();
								bounceMoving = true;
							}
							if (GameRuntime.checkButton(KeyCode.NUM5)
									|| GameRuntime.checkButton(KeyCode.NUM2)
									|| GameRuntime.checkButton(KeyCode.UP)
									|| GameRuntime.checkButton(KeyCode.SOFTKEY_MIDDLE)) {
								bounceObj.jump(false);
								bounceMoving = true;
							}
							if (bounceMoving) {
								if (bounceObj.eyeFrame != 1) {
									bounceObj.eyeFrame = 0;
								}
								bounceObj.idleAnimStartTimer = 3000;
							}
						} else if (EventObject.eventVars[1] == CONTROLLER_CANNON) {
							if (GameRuntime.checkButton(KeyCode.UP)) {
								currentCannon.rotateUp();
							}
							if (GameRuntime.checkButton(KeyCode.DOWN)) {
								currentCannon.rotateDown();
							}
							if (GameRuntime.checkButton(KeyCode.NUM5) || GameRuntime.checkButton(KeyCode.SOFTKEY_MIDDLE)) {
								currentCannon.fire();
							}
						}
						int i2 = EventObject.eventVars[4];
						updateEvents();
						if (EventObject.eventVars[4] == 0 && i2 != 0) {
							bounceObj.resetPhysics();
						}
						if (bounceObj.localObjectMatrix.translationY < rootLevelObj.allBBoxMinY && EventObject.eventVars[0] == 0) {
							setPlayerState(1); //die
						}
						if (eggCount == bonusLevelEggLimit && isBonusLevel(currentLevel)) {
							setPlayerState(2); //win
						}
						EventObject.eventVars[3] = bounceObj.ballForme;
						EventObject.eventVars[4] = (int) bounceObj.curVelocity;
						EventObject.eventVars[5] = (int) bounceObj.curXVelocity;
						EventObject.eventVars[6] = (int) bounceObj.curYVelocity;
						if (stolenColorsAnimationCountdown > 0) {
							stolenColorsAnimationCountdown -= GameRuntime.updateDelta;
							stolenColorsFlashCountdown -= GameRuntime.updateDelta;
							if (stolenColorsFlashCountdown <= 0) {
								stolenColorsFlashCountdown = Math.abs((mRNG.nextInt() % 200) + 300);
								isFlashToOtherColorMode = true;
							}
							if (stolenColorsAnimationCountdown <= 0) {
								stolenColorsAnimationCountdown = 0;
								stolenColorsFlashCountdown = 0;
								isFlashToOtherColorMode = false;
								isColorsAreStolen = !isColorsAreStolen;
							}
							for (int i = 0; i < ALL_PARALLAX_IMAGE_IDS.length; i++) {
								if (GameRuntime.getImageResource(ALL_PARALLAX_IMAGE_IDS[i]) != null) {
									if ((!isColorsAreStolen || isFlashToOtherColorMode) && (isColorsAreStolen || !isFlashToOtherColorMode)) {
										if (parallaxImagesRegColors[i] != null) {
											GameRuntime.replaceImageResource(ALL_PARALLAX_IMAGE_IDS[i], parallaxImagesRegColors[i]);
										}
									} else if (parallaxImagesStolenColors[i] != null) {
										GameRuntime.replaceImageResource(ALL_PARALLAX_IMAGE_IDS[i], parallaxImagesStolenColors[i]);
									}
								}
							}
						}
						break;
					case 1:
						GameRuntime.playMusic(SoundID.ME_LOSE, 1);
						setPlayerState(3);
						exitWaitTimer = 3000;
						f274c = bounceObj.localObjectMatrix.translationY;
						if (currentLevel == LevelID.FINAL_RIDE) {
							EventObject.finalBossTimer = 0;
						}
						break;
					case 2:
						GameRuntime.playMusic(SoundID.ME_WIN, 1);
						setPlayerState(4);
						exitWaitTimer = 3000;
						bounceObj.resetPhysics();
						if (isBonusLevel(currentLevel)) {
							bounceObj.enablePhysics = false;
						}
						winParticle.startEmitter(20, bounceObj.localObjectMatrix.translationX, bounceObj.localObjectMatrix.translationY, 740, 0, 1840, 230);
						break;
					case 3: //dying -> return to checkpoint
						exitWaitTimer -= GameRuntime.updateDelta;
						bounceObj.updateDeathAnimation();
						if (exitWaitTimer <= 0) {
							bounceObj.setPosXY(checkpointPosX, checkpointPosY);
							reqCameraSnap = true;
							setPlayerState(0);
							bounceObj.fadeColor = 0xFF000000;
							GameRuntime.playMusic(getLevelMusicID(), -1);
						}
						break;
					case 4:
						//exit level and update stats
						exitWaitTimer -= GameRuntime.updateDelta;
						bounceObj.jump(true);
						updateEvents();
						if (exitWaitTimer <= 0) {
							int e = getTimerChallengeRank(currentLevel);
							int d = getCollectionChallengeRank(currentLevel);
							short highScoreBefore = getLevelGlobalHighScore(currentLevel);
							boolean finalRideBeatenBefore = wasLevelBeaten(13);
							boolean superBounceUnlockedBefore = checkSuperBounceUnlocked();
							short levelClearTimeSeconds = (short) (levelTimer / 1000);
							if (levelClearTimeSeconds < 1) {
								levelClearTimeSeconds = 1;
							}
							int finalScore = (int) ((((float) (eggCount * 10000)) * EGG_SCORE_MULTIPLIER_BY_LEVEL[currentLevel]) / ((float) levelClearTimeSeconds));
							if (finalScore > 32767) {
								finalScore = 32767;
							}
							calcScore = (short) finalScore;
							updateLevelStats(currentLevel, (short) eggCount, levelClearTimeSeconds, (short) calcScore);
							if (!isBonusLevel(currentLevel)) {
								int nextLevel = currentLevel + 1;
								if (isBonusLevel(nextLevel)) {
									nextLevel++;
								}
								if (nextLevel <= LevelID.FANTASTIC_FAIR && !isLevelUnlocked(nextLevel)) {
									unlockLevel(nextLevel);
								}
							}
							int bonusChapterNo = 0;
							for (int bonusLevelIdx = 0; bonusLevelIdx < BONUS_LEVEL_INFO.length; bonusLevelIdx += 2) {
								if (getTotalEggCount() >= BONUS_LEVEL_INFO[bonusLevelIdx + 1]) { //required egg count for unlock
									int bonusLevelLevelId = BONUS_LEVEL_INFO[bonusLevelIdx];
									if (!isLevelUnlocked(bonusLevelLevelId)) {
										unlockLevel(bonusLevelLevelId);
										bonusChapterNo = (bonusLevelIdx >> 1) + 1;
									}
								}
							}
							if (!finalRideBeatenBefore && wasLevelBeaten(LevelID.FINAL_RIDE)) {
								this.wasFinalLevelJustBeaten = true;
							}
							if (!superBounceUnlockedBefore && checkSuperBounceUnlocked()) {
								this.wasSuperBounceJustUnlocked = true;
							}
							int e2 = getTimerChallengeRank(currentLevel);
							if (e2 > e) {
								this.timerChallengeTrophy = e2;
							}
							int d2 = getCollectionChallengeRank(currentLevel);
							if (d2 > d) {
								this.collectionChallengeTrophy = d2;
							}
							if (getLevelGlobalHighScore(currentLevel) > highScoreBefore) {
								this.highScoreBeaten = true;
							}
							serializeSaveData();
							if (bonusChapterNo > 0) {
								this.reqQuitLevelAfterFieldMessage = true;
								fieldMessageParam = new String[]{String.valueOf(bonusChapterNo)};
								pushFieldMessage(1);
							} else {
								levelEnded();
							}
						}
						break;
					default:
						break;
				}
				popFieldMessage();
			}
		}
		return 0;
	}

	/* renamed from: a */
	public final int paint(int lastPaintResult, int paintMode) {
		short[] sArr;
		short[] sArr2;
		short[] sArr3;
		Graphics grp = GameRuntime.getGraphicsObj();
		if (this.gameMainState == 2) { //splash screen/loading
			if (this.curSplashId < 0) {
				drawLoadingBar(grp);
				updateLoadingScreen();
			} else {
				grp.setColor(BACKGROUND_COLORS[this.curSplashId]);
				grp.fillRect(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
				int splashImageId = SPLASH_IMAGE_IDS[this.curSplashId];
				GameRuntime.drawImageRes(
						((GameRuntime.currentWidth - GameRuntime.getImageMapParam(splashImageId, 0)) / 2) + GameRuntime.getImageMapParam(splashImageId, 2),
						((GameRuntime.currentHeight - GameRuntime.getImageMapParam(splashImageId, 1)) / 2) + GameRuntime.getImageMapParam(splashImageId, 3),
						splashImageId
				);
				if (System.currentTimeMillis() - this.splashScreenStartTime > ((long) SPLASH_SCREEN_DURATIONS[this.curSplashId])) {
					updateLoadingScreen();
				}
			}
			return 0;
		}
		if (paintMode == 1) {
			if (this.gameMainState == 4) { //in-game
				GameRuntime.setBacklight(true);
				Graphics graphics = GameRuntime.getGraphicsObj();
				DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
				graphics.setClip(0, 0, f353x, f354y);
				int levelType = getLevelType(currentLevel);
				short[] sArr4 = f307i;
				short[] sArr5 = f311j;
				short[] sArr6 = f314k;
				short[] fixedPosParallaxes = f318l;
				int i4 = 5413606;
				int i5 = 7460351;
				if (levelType == 1) {
					short[] sArr8 = f322m;
					short[] sArr9 = f325n;
					short[] sArr10 = f328o;
					fixedPosParallaxes = f331p;
					i4 = 7263689;
					i5 = 10485759;
					sArr = sArr10;
					sArr2 = sArr9;
					sArr3 = sArr8;
				} else if (levelType == 2) {
					short[] sArr11 = f334q;
					short[] sArr12 = f337r;
					short[] sArr13 = f340s;
					fixedPosParallaxes = f343t;
					i4 = 7737588;
					i5 = 131610;
					sArr = sArr13;
					sArr2 = sArr12;
					sArr3 = sArr11;
				} else {
					sArr = sArr6;
					sArr2 = sArr5;
					sArr3 = sArr4;
				}
				if (isBonusLevel(currentLevel)) {
					i4 = 0xB400BD;
					i5 = 0xFD8C13;
				}
				int bgStripeW = GameRuntime.currentWidth;
				int bgHeight = GameRuntime.currentHeight;
				int bgStripeH = GameRuntime.currentHeight / 40;
				int i9 = (i4 >> 16) & 255;
				int i10 = (i4 >> 8) & 255;
				int i11 = i4 & 255;
				int i12 = 65536 / bgHeight;
				int i13 = (((i5 >> 16) & 255) - i9) * i12 * bgStripeH;
				int i14 = (((i5 >> 8) & 255) - i10) * i12 * bgStripeH;
				int i15 = ((i5 & 255) - i11) * i12 * bgStripeH;
				int i16 = i9 << 16;
				int i17 = i10 << 16;
				int i18 = i11 << 16;
				for (int bgY = 0; bgY < bgHeight; bgY += bgStripeH) {
					setBGColor(((i16 >> 16) << 16) + ((i17 >> 16) << 8) + (i18 >> 16), graphics);
					graphics.fillRect(0, bgY + 0, bgStripeW, bgStripeH);
					i16 += i13;
					i17 += i14;
					i18 += i15;
				}
				mRNG.setSeed(currentLevel + 1);
				if (levelType == 2) {
					drawBGParallax(fixedPosParallaxes, 5, 100, 600, 100, -80, 50, 2, -1, graphics);
					drawBGParallax(sArr, 20, 100, GameRuntime.getImageMapParam(sArr[0], ImageMap.PARAM_WIDTH), 0, -50, 0, 3, 1380668, graphics);
					drawBGParallax(sArr2, 50, 100, GameRuntime.getImageMapParam(sArr2[0], ImageMap.PARAM_WIDTH), 0, -30, 0, 3, 131610, graphics);
				} else {
					drawBGParallax(sArr, 20, 100, 200, 140, -200, 200, 5, -1, graphics);
					drawBGParallax(sArr2, 50, 100, 100, 70, -70, 300, 5, -1, graphics);
					drawBGParallax(sArr3, 80, 100, 150, 100, -70, 300, 5, -1, graphics);
				}
				mRNG.setSeed(System.currentTimeMillis());
				GameObject.drawRootObject(rootLevelObj, graphics, directGraphics);
				graphics.setClip(0, 0, GameRuntime.currentWidth, GameRuntime.currentHeight);
				GameRuntime.setTextStyle(-3, 1);
				GameRuntime.setTextColor(0, 0);
				GameRuntime.drawImageRes(0, 0, 102);
				int eggLimitX = drawStylizedNumber(32, 7, eggCount, Graphics.LEFT, false) + 32;
				GameRuntime.drawImageRes(eggLimitX, 7, 101); //divider slash (0/30)
				drawStylizedNumber(eggLimitX + 9, 7, bonusLevelEggLimit, Graphics.LEFT, false);
				GameRuntime.drawImageRes(GameRuntime.currentWidth, 0, 103);
				int levelSecondTimer = levelTimer / 1000;
				int levelTimeMinutes = levelSecondTimer / 60;
				int levelTimeSeconds = levelSecondTimer % 60;
				int timerSecondsX = ((GameRuntime.currentWidth - 25) - 6) - 1;
				int timerMinutesX = timerSecondsX - drawStylizedNumber(timerSecondsX, 7, levelTimeSeconds, Graphics.RIGHT, true);
				GameRuntime.drawImageResAnchored(timerMinutesX, 7, 100, Graphics.TOP | Graphics.RIGHT); //divider colon (00:00)
				drawStylizedNumber(timerMinutesX - 5, 7, levelTimeMinutes, Graphics.RIGHT, false);
				isFlashToOtherColorMode = false;
			}
			if (this.drawUI != null) {
				this.drawUI.draw();
			}
		} else if (paintMode == 2) { //loading
			drawLoadingBar(grp);
		}
		return 0;
	}

	/* renamed from: b */
	public final int loadScene(int sceneId, int sceneResult) {
		if (!(sceneId == 17 || sceneId == 25)) {
			lastMainMenuOption = 0;
		}
		switch (sceneId) {
			case 0:
				GameRuntime.startLoadScene(2);
				return 0;
			case 1:
				if (sceneResult >= 0 && sceneResult < SPLASH_SCREEN_LAYOUT_RESIDS.length) {
					if (sceneResult == 0) {
						this.curSplashId = -1;
						this.gameMainState = 2;
					}
					GameRuntime.loadResource(SPLASH_SCREEN_LAYOUT_RESIDS[sceneResult]);
					return sceneResult + 1;
				} else if (sceneResult != SPLASH_SCREEN_LAYOUT_RESIDS.length) {
					return 0;
				} else {
					System.out.println("Loading saved data...");
					freeIngameData();
					if (GameRuntime.isMusicEnabled()) {
						GameRuntime.loadResidentResSet(0);
					}
					byte[] savedData = GameRuntime.loadFromRecordStore("game");
					if (savedData != null) {
						deserializeSaveData(savedData);
						if (!wasLevelBeaten(LevelID.FINAL_RIDE)) {
							int levelId = 14;
							while (true) {
								if (levelId >= 0) {
									if (!isLevelUnlocked(levelId) || isBonusLevel(levelId)) {
										levelId--;
									} else {
										selectedLevelId = levelId;
									}
								}
							}
						}
					} else {
						clearSaveData();
						unlockLevel(0);
					}
					System.out.println("Saved data loaded");
					return 0;
				}
			case 2:
				if (sceneResult == 0) {
					return 1;
				}
				if (sceneResult == 1) {
					return 2;
				}
				if (sceneResult != 2) {
					return 0;
				}
				if (this.drawUI == null || this.drawUI.currentStage == 14) {
					GameRuntime.setMusicEnabled(true);
					GameRuntime.startLoadScene(1);
				} else {
					setUI(19);
				}
				return 0;
			case 5: //return to level select
				if (sceneResult == 0) {
					GameRuntime.stopMusic();
					freeIngameData();
					return 1;
				} else if (sceneResult != 1) {
					return 0;
				} else {
					this.gameMainState = 3;
					setUI(mainMenuReturnUI);
					GameRuntime.playMusic(SoundID.TITLE_MENU, -1);
					return 0;
				}
			case 6:
			case 15: //load level
				if (sceneResult == 0) {
					GameRuntime.stopMusic();
					GameRuntime.unloadResource(10);
					GameRuntime.unloadResource(13);
					GameRuntime.loadResource(LEVEL_RESIDS[currentLevel]);
					GameRuntime.loadResource(LEVEL_RESIDS[CANNON_LEVEL_INDEX]);
					GameRuntime.loadResource(2);
					GameRuntime.loadResource(1);
					GameRuntime.loadResource(6);
					GameRuntime.loadResource(5);
					GameRuntime.loadResource(24);
					GameRuntime.loadResource(0);
					GameRuntime.loadResource(3);
					GameRuntime.loadResource(26);
					GameRuntime.loadResource(20);
					GameRuntime.loadResource(19);
					GameRuntime.loadResource(16);
					GameRuntime.loadResource(25);
					GameRuntime.loadResource(27);
					GameRuntime.loadResource(21);
					GameRuntime.loadResource(23);
					GameRuntime.loadResource(22);
					int b = getLevelType(currentLevel);
					if (b == 0) {
						GameRuntime.loadResource(12);
						GameRuntime.loadResource(17);
						GameRuntime.loadResource(18);
					}
					if (b == 1) {
						GameRuntime.loadResource(11);
						GameRuntime.loadResource(17);
						GameRuntime.loadResource(18);
					}
					if (b == 2) {
						GameRuntime.loadResource(8);
						GameRuntime.loadResource(4);
						GameRuntime.loadResource(17);
					}
					ballFramebuffer = Image.createImage(BounceObject.BALL_DIMENS_SCREENSPACE[0] * 3, BounceObject.BALL_DIMENS_SCREENSPACE[0] * 3);
					ballGraphics = ballFramebuffer.getGraphics();
					ballFramebufferRGB = new int[(ballFramebuffer.getWidth() * ballFramebuffer.getHeight())];
					Image createImage2 = Image.createImage(112, 26);
					f266b = createImage2;
					f265b = createImage2.getGraphics();
					f270b = new int[(f266b.getWidth() * f266b.getHeight())];
					return 1;
				} else if (sceneResult != 1) {
					return 0;
				} else {
					if (!this.isLevelActive) {
						this.isLevelActive = true;
						levelPaused = false;
						GameRuntime.setUpdatesPerDraw(2);
						GameRuntime.setMaxUpdateDelta(150 / GameRuntime.getUpdatesPerDraw());
						isSuperBounceUnlocked = checkSuperBounceUnlocked();
						GameObject.cameraBounceFactor = 90;
						GameObject.cameraStabilizeSpeed = 140;
						levelTimer = 0;
						totalGameTime = 0;
						eggCount = 0;
						calcScore = 0;
						isFieldMessageShowing = false;
						f255a = false;
						isBlockingEvent = false;
						GameObject.screenSpaceMatrix.translationX = GameRuntime.currentWidth << 15;
						GameObject.screenSpaceMatrix.translationY = GameRuntime.currentHeight << 15;
						byte[] cannonLevel = (byte[]) GameRuntime.getLoadedResData((int) LEVEL_RESIDS[CANNON_LEVEL_INDEX]);
						cannonModels = new GameObject[GameObject.readShort(cannonLevel, 8)];
						byte cmnKey;
						short cmnObjectId = 0;
						int maxVerticesPerObj = 0;
						int pos = 14;
						while ((cmnKey = cannonLevel[pos++]) != LevelKey.END) {
							short dataSize = GameObject.readShort(cannonLevel, pos);
							pos += 2;
							switch (cmnKey) {
								case LevelKey.GEOMETRY:
									GeometryObject geometry = new GeometryObject();
									geometry.setObjectId(cmnObjectId);
									geometry.readData(cannonLevel, pos);
									if (geometry.getVertexCount() > maxVerticesPerObj) {
										maxVerticesPerObj = geometry.getVertexCount();
									}
									cannonModels[cmnObjectId++] = geometry;
									break;
								default:
									break;
							}
							pos += dataSize;
						}
						byte[] levelData = (byte[]) GameRuntime.getLoadedResData((int) LEVEL_RESIDS[currentLevel]);
						int playerDataOffs = 0;
						if (levelData != null) {
							objectCount = GameObject.readShort(levelData, 8);
							levelObjects = new GameObject[objectCount];
							eventCount = GameObject.readShort(levelData, 12);
							events = new EventObject[eventCount];
							int currentBytePos = 15;
							byte key = levelData[14];
							short objID = 0;
							int eventId = 0;
							bonusLevelEggLimit = 0;
							while (key != LevelKey.END) {
								short dataLength = GameObject.readShort(levelData, currentBytePos);
								int afterHeaderPos = currentBytePos + 2;
								switch (key) {
									case LevelKey.GEOMETRY: {
										GeometryObject geometry = new GeometryObject();
										geometry.setObjectId(objID);
										geometry.readData(levelData, afterHeaderPos);
										if (geometry.getVertexCount() > maxVerticesPerObj) {
											maxVerticesPerObj = geometry.getVertexCount();
										}
										levelObjects[objID] = geometry;
										break;
									}
									case 5:
									case 7:
									default:
										levelObjects[objID] = new GameObject();
										levelObjects[objID].setObjectId(objID);
										levelObjects[objID].readData(levelData, afterHeaderPos);
										break;
									case LevelKey.EVENT:
										EventObject objEv = new EventObject();
										objEv.setObjectId(objID);
										objEv.readData(levelData, afterHeaderPos);
										levelObjects[objID] = objEv;
										events[eventId++] = objEv;
										break;
									case LevelKey.PLAYER:
										BounceObject player = new BounceObject(true);
										bounceObj = player;
										player.setObjectId(objID);
										playerDataOffs = afterHeaderPos;
										player.readData(levelData, afterHeaderPos);
										player.initialize();
										levelObjects[objID] = player;
										checkpointPosX = player.localObjectMatrix.translationX;
										checkpointPosY = player.localObjectMatrix.translationY;
										break;
									case LevelKey.SPRITE:
										levelObjects[objID] = new SpriteObject();
										levelObjects[objID].setObjectId(objID);
										levelObjects[objID].readData(levelData, afterHeaderPos);
										levelObjects[objID].initialize();
										break;
									case LevelKey.WATER:
										WaterObject water = new WaterObject();
										water.setObjectId(objID);
										water.readData(levelData, afterHeaderPos);
										water.initialize();
										if (water.vertexCount > maxVerticesPerObj) {
											maxVerticesPerObj = water.vertexCount;
										}
										levelObjects[objID] = water;
										break;
									case LevelKey.CANNON:
										CannonObject cannon = new CannonObject();
										cannon.setObjectId(objID);
										cannon.readData(levelData, afterHeaderPos);
										cannon.initialize();
										levelObjects[objID] = cannon;
										break;
									case LevelKey.TRAMPOLINE:
										TrampolineObject jumpPad = new TrampolineObject();
										jumpPad.setObjectId(objID);
										jumpPad.readData(levelData, afterHeaderPos);
										jumpPad.initialize();
										levelObjects[objID] = jumpPad;
										break;
									case LevelKey.EGG:
										EggObject egg = new EggObject();
										egg.setObjectId(objID);
										egg.readData(levelData, afterHeaderPos);
										egg.initialize();
										levelObjects[objID] = egg;
										bonusLevelEggLimit++;
										break;
									case LevelKey.FRIEND: {
										BounceObject friend = new BounceObject(false);
										friend.setObjectId(objID);
										friend.readData(levelData, afterHeaderPos);
										friend.initialize();
										levelObjects[objID] = friend;
										break;
									}
									case LevelKey.ENEMY: {
										EnemyObject enemy = new EnemyObject();
										enemy.setObjectId(objID);
										enemy.readData(levelData, afterHeaderPos);
										enemy.initialize();
										levelObjects[objID] = enemy;
										bonusLevelEggLimit++;
										break;
									}
								}
								objID++;
								int nextKeyIndex = afterHeaderPos + dataLength;
								currentBytePos = nextKeyIndex + 1;
								key = levelData[nextKeyIndex];
							}
							for (int i = 0; i < levelObjects.length; i++) {
								if (levelObjects[i] == null) {
									System.err.println("Object with ID " + i + " is ABSENT!!");
								}
							}
							GameObject.makeObjectLinks(levelObjects);
							//BUGFIX: BounceObject physics only work if the object isn't parented to anything
							for (int i = 0; i < levelObjects.length; i++) {
								if (levelObjects[i].getObjType() == BounceObject.TYPEID) {
									levelObjects[i].makeIndependent();
								}
							}
							rootLevelObj = levelObjects[0];
							levelObjects = null;
							enemyDeadEgg = new EggObject();
							enemyDeadEgg.setObjectId(objID++);
							enemyDeadEgg.setParent(rootLevelObj);
							enemyDeadEgg.localObjectMatrix.m00 = LP32.ONE;
							enemyDeadEgg.localObjectMatrix.m01 = 0;
							enemyDeadEgg.localObjectMatrix.translationX = Integer.MAX_VALUE;
							enemyDeadEgg.localObjectMatrix.m10 = 0;
							enemyDeadEgg.localObjectMatrix.m11 = LP32.ONE;
							enemyDeadEgg.localObjectMatrix.translationY = Integer.MAX_VALUE;
							enemyDeadEgg.renderCalcMatrix.setFromMatrix(enemyDeadEgg.localObjectMatrix);
							enemyDeadEgg.initialize();
							swimParticle.setObjectId(objID++);
							swimParticle.attachToObject(rootLevelObj);
							f267b.setObjectId(objID++);
							f267b.attachToObject(rootLevelObj);
							f285e.setObjectId(objID++);
							f285e.attachToObject(rootLevelObj);
							f290f.setObjectId(objID++);
							f290f.attachToObject(rootLevelObj);
							enemyDeathParticle.setObjectId(objID++);
							enemyDeathParticle.attachToObject(rootLevelObj);
							winParticle.setObjectId(objID++);
							winParticle.attachToObject(rootLevelObj);
							f280d.setObjectId(objID++);
							f280d.attachToObject(rootLevelObj);
							f300h.setObjectId(objID++);
							f300h.attachToObject(rootLevelObj);
							waterParticle.setObjectId(objID++);
							waterParticle.attachToObject(rootLevelObj);
							GameObject.allocateRenderPool(objID); //okay to be a bit much, it's just a pointer array
							GeometryObject.TEMP_QUAD_XS = new int[maxVerticesPerObj];
							GeometryObject.TEMP_QUAD_YS = new int[maxVerticesPerObj];
							int[] iArr = new int[72];
							EventObject.eventVars = iArr;
							iArr[1] = 0;
							setPlayerState(0);
							EventObject.eventVars[2] = 0;
							EventObject.eventVars[7] = 0;
							GameRuntime.unloadResource(LEVEL_RESIDS[currentLevel]);
							GameRuntime.unloadResource(LEVEL_RESIDS[CANNON_LEVEL_INDEX]);
							GameObject.cameraTarget = bounceObj;
							GameObject.snapCameraToTarget();
							f240F = GameObject.cameraMatrix.translationY;
							initStolenColorData();
							f267b.particleCount = -1;
							swimParticle.particleCount = -1;
							f285e.particleCount = -1;
							f290f.particleCount = -1;
							enemyDeathParticle.particleCount = -1;
							winParticle.particleCount = -1;
							f280d.particleCount = -1;
							f300h.particleCount = -1;
							waterParticle.particleCount = -1;
							bounceObj.fadeColor = 0xFF000000;
							boolean noBonusLevelsBeaten = true;
							for (int bonusLevelIdx = 0; bonusLevelIdx < BONUS_LEVEL_INFO.length; bonusLevelIdx += 2) {
								if (wasLevelBeaten(BONUS_LEVEL_INFO[bonusLevelIdx])) {
									noBonusLevelsBeaten = false;
								}
							}
							if (noBonusLevelsBeaten && isBonusLevel(currentLevel)) {
								pushFieldMessage(11);
							}
							bounceObj.eyeFrame = 0;
							bounceObj.idleAnimStartTimer = 3000;
						}
					}
					this.gameMainState = 4;
					setIngameHID();
					if (!levelPaused) {
						GameRuntime.playMusic(getLevelMusicID(), -1);
					}
					return 0;
				}
			case 11: //start NG+
				for (int saveIndex = 0; saveIndex < levelSaveData.length; saveIndex++) {
					if ((saveIndex + 1) % 4 != 0) {
						levelSaveData[saveIndex] = 0;
					}
				}
				unlockLevel(0);
				selectedLevelId = 0;
				serializeSaveData();
				this.isLevelActive = false;
				setUI(18);
				return 0;
			case 21:
			case 22:
				lastMainMenuOption = this.ui.getSelectedOption();
				setUI(sceneId);
				return 0;
			case 27:
				return 0;
			case 36: //go to main menu
				if (sceneResult == 0) {
					GameRuntime.unloadResource(SPLASH_SCREEN_LAYOUT_RESIDS[SPLASH_SCREEN_LAYOUT_RESIDS.length - 1]);
					return 1;
				} else if (sceneResult != 1) {
					return 0;
				} else {
					this.gameMainState = 3;
					GameRuntime.playMusic(SoundID.TITLE_MENU, -1);
					setUI(17);
					return 0;
				}
			default:
				return 0;
		}
	}

	/* renamed from: b */
	public final void handleKeyPress(int keyCode) {
		if (this.drawUI != null) {
			UILayout ui = this.drawUI;
			if (ui.currentStage == 18) {
				if (enableCheats) {
					if (CHEAT_COMBO_ALL_UNLOCK[cheatComboIndex] == keyCode) {
						if (++cheatComboIndex == CHEAT_COMBO_ALL_UNLOCK.length) {
							System.out.println("Cheat activated: unlock all levels");
							for (int levelId = 0; levelId < LevelID.LEVEL_IDX_MAX; levelId++) {
								debugLevelUnlock(levelId);
							}
							cheatComboIndex = 0;
							updateLevelStartSoftkeyByUnlock(ui);
							serializeSaveData();
						}
					} else if (CHEAT_COMBO_ALL_COMPLETE[cheatComboIndex] == keyCode) {
						if (++cheatComboIndex == CHEAT_COMBO_ALL_COMPLETE.length) {
							System.out.println("Cheat activated: complete all levels");
							for (int levelId = 0; levelId < LevelID.LEVEL_IDX_MAX; levelId++) {
								debugLevelUnlock(levelId);
								updateLevelStats(levelId, (short) 30, (short) 1, (short) 9999);
							}
							cheatComboIndex = 0;
							updateLevelStartSoftkeyByUnlock(ui);
							serializeSaveData();
						}
					} else {
						cheatComboIndex = 0;
					}
				}
				switch (keyCode) {
					case KeyCode.LEFT:
						cycleLevelSelectLeft(ui, true);
						break;
					case KeyCode.RIGHT:
						cycleLevelSelectRight(ui, true);
						break;
				}
			}
			this.drawUI.handleKeyCode(keyCode);
		} else if (this.gameMainState == 2) {
			updateLoadingScreen();
		} else if (this.gameMainState == 4) {
			if (enableCheats) {
				//automatically win the level
				if (CHEAT_COMBO_ALL_UNLOCK[cheatComboIndex] == keyCode && (EventObject.eventVars[1] == CONTROLLER_NORMAL || EventObject.eventVars[1] == CONTROLLER_CANNON)) {
					if (++cheatComboIndex == CHEAT_COMBO_ALL_UNLOCK.length) {
						setPlayerState(2);
						cheatComboIndex = 0;
						eggCount = 30;
					}
				} else {
					cheatComboIndex = 0;
				}
			}
			switch (keyCode) {
				case KeyCode.SOFTKEY_RIGHT:
					levelPaused = true;
					break;
				case KeyCode.STAR:
					if (EventObject.eventVars[1] == CONTROLLER_NORMAL && EventObject.eventVars[0] != 3) {
						bounceObj.cycleForme();
					}
					break;
			}
		}
	}

	/* renamed from: c */
	public final void onSystemEvent(int eventId) {
		if (eventId == GameRuntime.SYSTEM_EVENT_START) {
			GameRuntime.loadResource(9); //game UI
			GameRuntime.loadResource(14);
			GameRuntime.loadResource(15);
			GameRuntime.loadResource(37);
			GameRuntime.loadResource(36);
			GameRuntime.loadResource(-1);
			GameRuntime.loadResource(-2);
			GameRuntime.loadResource(-3);
			GameRuntime.startLoadScene(0);
		} else if (eventId == GameRuntime.SYSTEM_EVENT_PAUSE && this.gameMainState == 4) {
			levelPaused = true;
			if (isFieldMessageShowing) {
				reqReloadFieldMsg = true;
				isFieldMessageShowing = false;
				setIngameHID();
			} else if (!reqReloadFieldMsg) {
				lastFieldMsg = null;
			}
		}
	}

	/* renamed from: d */
	public final void changeScene(final int sceneId) {
		if (sceneId != 17 && sceneId != 25) {
			lastMainMenuOption = 0;
		}
		switch (sceneId) {
			case 2:
			case 5:
			case 6:
			case 11:
			case 15:
			case 21:
			case 22:
			case 27: {
				GameRuntime.startLoadScene(sceneId);
				break;
			}
			case 10:
			case 20:
			case 28:
			case 29:
			case 30: {
				lastMainMenuOption = this.ui.getSelectedOption();
			}
			case 17:
			case 18:
			case 19:
			case 23:
			case 24:
			case 25:
			case 26:
			case 31:
			case 32:
			case 33: {
				this.setUI(sceneId);
				break;
			}
			case 9: {
				GameRuntime.quit();
				break;
			}
			case 35: { //field message advance
				isFieldMessageShowing = false;
				this.setIngameHID();
				if (reqQuitLevelAfterFieldMessage) {
					reqQuitLevelAfterFieldMessage = false;
					levelEnded();
				}
				break;
			}
			case 13: {
				break;
			}
			case 37: { //restart level
				resetParallaxStolenColors();
				//fall through
			}
			case 7: {
				if (isLevelUnlocked(selectedLevelId)) { //enter level
					f321m = fieldMessageQueue;
					this.isLevelActive = false;
					currentLevel = selectedLevelId;
					GameRuntime.startLoadScene(6); //load level
				}
				break;
			}
			case 8: { //unpause
				levelPaused = false;
				this.setIngameHID();
				if (!levelPaused) {
					GameRuntime.playMusic(getLevelMusicID(), -1);
					break;
				}
				break;
			}
		}
	}
}