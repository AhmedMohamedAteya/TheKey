package apps.pixel.the.key.fragments.menu;

import java.util.List;

import apps.pixel.the.key.models.menu.MainMenu;
import apps.pixel.the.key.models.menu.SubMenu;

public interface MenuInterface {
    void getMenuData(List<MainMenu> mainMenus);

    void getSubMenuData(List<SubMenu> subMenus);
}
