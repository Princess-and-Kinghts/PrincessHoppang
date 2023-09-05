package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "category_id")
    private Long categoryId;
    @Column(nullable = false)
    @JoinColumn(name = "category")
    private String category;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();
}
