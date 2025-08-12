package rw.andremugabo.backend_todo.core.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.andremugabo.backend_todo.core.base.AbstractBaseDtoEntity;

import javax.swing.text.StyledEditorKit;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class TodoDto extends AbstractBaseDtoEntity {
    private String title;
    private String description;
    private Boolean completed;
}
