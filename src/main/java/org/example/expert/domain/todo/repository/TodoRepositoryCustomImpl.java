package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.todo.entity.QTodo.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<Todo> findByIdWithUser(Long todoId) {
    Todo findTodo = jpaQueryFactory
        .select(todo)
        .from(todo)
        .leftJoin(todo.user).fetchJoin()
        .where(todo.id.eq(todoId))
        .fetchOne();
    return Optional.ofNullable(findTodo);
  }
}