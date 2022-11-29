import "../../assets/styles/components/main/post.scss"
import logo from "../../TEST.png"

const Post = () => {
    return (
        <div className="post">
            <img className="post__image" src={logo} alt="post"/>
            <div className="post__buttons">buttons</div>
            <p className="post__description"><strong>username </strong>descr</p>
        </div>
    );
};

export default Post;