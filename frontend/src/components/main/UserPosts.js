import {useEffect, useState} from "react";
import * as PostService from "../../services/postService";
import * as UserService from "../../services/userService";
import Post from "./Post";
import "../../assets/styles/components/main/userposts.scss"

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

    const noPosts = (
        <div className="no-posts">
            <span className="icon image extra-large"></span>
            <br/>
            <p>There is no posts yet</p>
        </div>
    );

    return (
        <div className="posts">
            {posts.length ? postList : noPosts}
        </div>
    );
};

export default UserPosts;