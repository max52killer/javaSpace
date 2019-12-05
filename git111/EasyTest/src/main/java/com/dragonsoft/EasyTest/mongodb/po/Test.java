package com.dragonsoft.EasyTest.mongodb.po;

import com.dragonsoft.EasyTest.mongodb.anno.Collect;
import com.dragonsoft.EasyTest.mongodb.anno.Column;
import com.dragonsoft.EasyTest.mongodb.anno.Id;

@Collect(name="test")
public class Test {

    @Id
    @Column(name="_id")
    private String _id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="likes")
    private String likes;
    @Column(name="by")
    private String by;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
