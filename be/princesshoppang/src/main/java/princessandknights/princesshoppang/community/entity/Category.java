package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import princessandknights.princesshoppang.community.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    @JoinColumn(name = "category_title")
    private String categoryTitle;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    public static Category toSaveEntity(CategoryDto categoryDto, Post post) {


        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryTitle(categoryDto.getCategory())
//                .posts(Collections.singletonList(post))
                .build();
    }
}
