package ru.job4j.tracker;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        boolean rsl = false;
        out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        try {
            tracker.delete(id);
            out.println("Заявка удалена успешно.");
            rsl = true;
        } catch (Exception e) {
            out.println("Ошибка удаления заявки.");
        }
        return rsl;
    }
}
