package bouncetales;

/**
 *
 * @author vipaol
 */
public class SettingsUI {
    private int currentSelectedIndex = 0;
    private int descriptionW;
    private SettingsElement[] items = new SettingsElement[]{
        new SettingsElement() {
            String getTitle() {
                return "Water drawing workaround";
            }

            String getDescription() {
                return "Nokiaui DirectGraphics API is used to draw water. "
                        + "If your device doesn't support nokiaui then you "
                        + "need to enable this to see water. "
                        + "IT IS VERY LAGGY";
            }

            void setIsEnabled(boolean b) {
                Settings.enableWaterNoDirectGraphicsWorkaround = b;
            }

            boolean getIsEnabled() {
                return Settings.enableWaterNoDirectGraphicsWorkaround;
            }

            void toggle() {
                setIsEnabled(!getIsEnabled());
            }
        },
        new SettingsElement() {
            String getTitle() {
                return "Disable setting max brightness";
            }

            String getDescription() {
                return "Disables setting maximum brightness with nokiaui";
            }

            void setIsEnabled(boolean b) {
                Settings.disableMaxBrightness = b;
            }

            boolean getIsEnabled() {
                return Settings.disableMaxBrightness;
            }

            void toggle() {
                setIsEnabled(!getIsEnabled());
            }
        }
    };

    public SettingsUI(int descriptionW) {
        this.descriptionW = descriptionW;
    }

    boolean nextPage() {
        if (currentSelectedIndex < items.length - 1) {
            currentSelectedIndex++;
            return true;
        }
        return false;
    }

    boolean prevPage() {
        if (currentSelectedIndex > 0) {
            currentSelectedIndex--;
            return true;
        }
        return false;
    }

    public int getCurrentSelectedItemIndex() {
        return currentSelectedIndex;
    }

    public SettingsElement getElementAt(int i) {
        return items[i];
    }

    public int getItemsCount() {
        return items.length;
    }

    void toggleSelected() {
        items[currentSelectedIndex].toggle();
        System.out.println(items[currentSelectedIndex].getTitle() + items[currentSelectedIndex].getIsEnabled());
    }

    String getMiddleBtnTitle() {
        return "Toggle";
    }

    SettingsElement getCurrentSelectedItem() {
        return getElementAt(getCurrentSelectedItemIndex());
    }

    public abstract class SettingsElement {
        TextLabel descriptionLabel = null;

        public void drawDescription(int centerX, int topY) {
            if (descriptionLabel == null) {
                descriptionLabel = new TextLabel(getDescription(), descriptionW, -1, 0, -1);
            }
            descriptionLabel.draw(centerX - descriptionW / 2, topY, 0, 0);
        }

        abstract String getTitle();
        abstract String getDescription();
        abstract void setIsEnabled(boolean b);
        abstract boolean getIsEnabled();
        abstract void toggle();
    }
}
