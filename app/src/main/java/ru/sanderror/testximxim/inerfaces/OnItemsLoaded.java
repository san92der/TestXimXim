package ru.sanderror.testximxim.inerfaces;

import java.util.List;

import ru.sanderror.testximxim.models.ListItem;

public interface OnItemsLoaded {
    void onLoaded(List<ListItem> response);
}
