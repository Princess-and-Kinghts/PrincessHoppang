package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;

@Entity
public class Emotion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmotionId;

    @OneToOne
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Post post;

    @JoinColumn(name = "like")
    @Column(nullable = false)
    private Long like;
    @JoinColumn(name = "agree")
    @Column(nullable = false)
    private Long agree;

    @JoinColumn(name = "eh")
    @Column(nullable = false)
    private Long eh;
    @JoinColumn(name = "aww")
    @Column(nullable = false)
    private Long aww;
    @JoinColumn(name = "annoy")
    @Column(nullable = false)
    private Long annoy;

    @JoinColumn(name = "sad")
    @Column(nullable = false)
    private Long sad;
    @JoinColumn(name = "idtlt")
    @Column(nullable = false)
    private Long idtlt;

}
