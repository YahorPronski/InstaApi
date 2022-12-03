import {useEffect, useState} from "react";
import * as PostService from "../../services/postService";
import * as UserService from "../../services/userService";
import Post from "./Post";

const UserPosts = ({userId}) => {
    const [user, setUser] = useState({});
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        UserService.getUserById(userId).then(setUser);
        PostService.getPostsByUserId(userId).then(setPosts);
    }, []);

    const postList = posts
        .sort((a, b) => a.creationDate < b.creationDate ? 1 : -1)
        .map(post =>
            <Post key={post.id} user={user} post={post}/>
        );

    return <div>{postList}</div>;
};

export default UserPosts;