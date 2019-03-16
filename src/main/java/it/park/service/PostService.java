package it.park.service;

import it.park.service.ru.itpark.repository.PostRepository;
import ru.itpark.domain.Post;

public class PostService {
    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;

    }

    private int nextId = 1;

    public void create(String name, String[] tags) {
        Post post = new Post(nextId, name, tags);
        repository.add(post);

    }

    public Post[] search(String text) {
        if (text.startsWith("#")) {
            String tag = text.substring(1);
            return searchByTag(text);
            //ищем по тегу
        }
        // ищем по названию

        return searchByName(text);
    }

    public Post[] searchByName(String name) {
        Post[] result = new Post[10];
        int resultIndex = 0;
        Post[] posts = repository.getAll();
        for (Post post : posts) {
            if (post == null) {
                continue;
            }
            if (post.getName().contains(name)) {
                continue;
            }

            String[] tags = post.getTags(); //дац мне все теги, которые в статье
            for (String tag : tags) {

            }

            result[resultIndex]=post;
            resultIndex++;
        }
        return result;
    }

    public Post[] searchByTag(String text) {
        Post[] result = new Post[10];
        int resultIndex = 0;
        Post[] posts = repository.getAll();
        for (Post post : posts) {
            if (post == null) {
                continue;
            //}
            //if (post.getName().contains(name)) {
             //   continue;
           // }

            String[] tags = post.getTags(); //дает мне все теги, которые в статье
            for (String tag : tags) {
                if (tag.equals(text)){
                    result[resultIndex] = post;
                    resultIndex++;
                }
            }

            }

            result[resultIndex]=post;
            resultIndex++;
        }
        return result;

    }
}
