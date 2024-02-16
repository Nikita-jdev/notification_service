package faang.school.notificationservice.entity.recommendation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import faang.school.notificationservice.entity.Skill;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "skill_offer")
public class SkillOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    public Skill skill;

    @ManyToOne
    @JoinColumn(name = "recommendation_id", nullable = false)
    private Recommendation recommendation;
}