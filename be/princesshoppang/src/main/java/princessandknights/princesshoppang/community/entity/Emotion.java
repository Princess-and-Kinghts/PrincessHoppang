package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import princessandknights.princesshoppang.user.entity.User;

@Slf4j
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emotion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmotionId;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @JoinColumn(name = "good")
    @Column(nullable = false)
    private int good;
    @JoinColumn(name = "agree")
    @Column(nullable = false)
    private int agree;

    @JoinColumn(name = "eh")
    @Column(nullable = false)
    private int eh;
    @JoinColumn(name = "aww")
    @Column(nullable = false)
    private int aww;
    @JoinColumn(name = "annoy")
    @Column(nullable = false)
    private int annoy;

    @JoinColumn(name = "sad")
    @Column(nullable = false)

    private int sad;
    @JoinColumn(name = "idtlt")
    @Column(nullable = false)
    private int idtlt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
