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
    private Long categoryId;

    @Column(nullable = false)
    private String categoryTitle;

    // 연관관계는 사라져도 자식엔티티가 제거되진 않음
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();

    public static Category toSaveEntity(CategoryDto categoryDto, Post post) {


        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryTitle(categoryDto.getCategory())
//                .posts(Collections.singletonList(post))
                .build();
    }
}
