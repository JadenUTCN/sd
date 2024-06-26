package com.utcn.demo.model;

import lombok.*;
import com.utcn.demo.audit.Auditable;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer extends Auditable<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", columnDefinition = "text", length = 65536, nullable = false)
    private String content;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "is_accepted")
    private Boolean isAccepted = false;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "answer_like",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> positiveVotes;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "answer_dislike",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> negativeVotes;
    public void addPositiveVote(Account author) {
        if (positiveVotes == null) {
            positiveVotes = new HashSet<>();
        }
        author.addScore();
        positiveVotes.add(author);
    }

    public void removePositiveVote(Account author) {
        positiveVotes.remove(author);
    }

    public void addNegativeVote(Account author) {
        if (negativeVotes == null) {
            negativeVotes = new HashSet<>();
        }
        author.subScore();
        negativeVotes.add(author);
    }

    public void removeNegativeVote(Account author) {
        negativeVotes.remove(author);
    }

    public Integer getRating() {
        if (positiveVotes != null && negativeVotes != null) {
            return positiveVotes.size() - negativeVotes.size();
        }
        return 0;
    }

    public String getFormattedDate(LocalDateTime timestamp, String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return timestamp.format(formatter);
    }

}
