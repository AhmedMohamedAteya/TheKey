package apps.pixel.al.egykey.fragments.menu;

import java.util.List;

import apps.pixel.al.egykey.models.menu.MainMenu;
import apps.pixel.al.egykey.models.menu.SubMenu;

public interface MenuInterface {
    void getMenuData(List<MainMenu> mainMenus);

    void getSubMenuData(List<SubMenu> subMenus);
}
