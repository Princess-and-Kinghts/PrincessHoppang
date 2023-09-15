package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import princessandknights.princesshoppang.community.dto.EmotionDto;
import princessandknights.princesshoppang.user.entity.User;

import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(nullable = false)
    private int good;


    @Column(nullable = false)
    private int agree;


    @Column(nullable = false)
    private int eh;


    @Column(nullable = false)
    private int aww;


    @Column(nullable = false)
    private int annoy;


    @Column(nullable = false)
    private int sad;


    @Column(nullable = false)
    private int idtlt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Emotion toSaveEntity(Long postId, EmotionDto emotionDto) {

        String emotionCategory = emotionDto.getEmotionCategory();
        int value = emotionDto.getValue();
        System.out.println("entity안 : "+ postId);
        System.out.println("entity안 : "+ emotionDto.getUserId());


        Post post = Post.builder()
                .postId(postId)
                .build();

        User user = User.builder()
                .userId(emotionDto.getUserId())
                .build();

        Emotion emotion = Emotion.builder()
                .post(post)
                .user(user)
                .good(0)
                .agree(0)
                .eh(0)
                .aww(0)
                .annoy(0)
                .sad(0)
                .idtlt(0)
                .build();

        switch (emotionCategory) {
            case "good":
                emotion.setGood(value);
                break;
            case "agree":
                emotion.setAgree(value);
                break;
            case "eh":
                emotion.setEh(value);
                break;
            case "aww":
                emotion.setAww(value);
                break;
            case "annoy":
                emotion.setAnnoy(value);
                break;
            case "sad":
                emotion.setSad(value);
                break;
            case "idtlt":
                emotion.setIdtlt(value);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 emotion: " + emotionCategory);
        }
        System.out.println("dmdkr " + emotion.getUser().getUserId());
        System.out.println("ㅈ제발 " + emotion.getPost().getPostId());
        return emotion;
    }


    public static Emotion updateExistingEmotion(Emotion existingEmotion, EmotionDto emotionDto) {
        String emotionCategory = emotionDto.getEmotionCategory();
        int value = emotionDto.getValue();

        existingEmotion.setGood(0);
        existingEmotion.setAgree(0);
        existingEmotion.setEh(0);
        existingEmotion.setAww(0);
        existingEmotion.setAnnoy(0);
        existingEmotion.setSad(0);
        existingEmotion.setIdtlt(0);

        switch (emotionCategory) {
            case "good":
                existingEmotion.setGood(value);
                break;
            case "agree":
                existingEmotion.setAgree(value);
                break;
            case "eh":
                existingEmotion.setEh(value);
                break;
            case "aww":
                existingEmotion.setAww(value);
                break;
            case "annoy":
                existingEmotion.setAnnoy(value);
                break;
            case "sad":
                existingEmotion.setSad(value);
                break;
            case "idtlt":
                existingEmotion.setIdtlt(value);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 emotion: " + emotionCategory);
        }
        return existingEmotion;

    }
}
