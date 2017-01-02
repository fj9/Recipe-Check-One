package com.recipecheckone.manager;

import com.recipecheckone.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ajix on 29/12/2016.
 */
public interface RecipeReplyRepository extends JpaRepository<Reply, Long> {
}
