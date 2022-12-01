import "../../assets/styles/components/main/post.scss"
import "../../assets/styles/common/icons.scss"
import logo from "../../TEST.png"

const Post = () => {
    return (
        <div className="post">
            <img className="post__image" src={logo} alt="post"/>
            <div className="post__info">
                <div className="post__buttons">
                    <span className="icon heart medium"></span>
                    <span className="icon comment medium"></span>
                    <span className="icon paper-plane medium"></span>
                    <span className="icon bookmark medium"></span>
                </div>
                <p className="post__likes">2,867 likes</p>
                <p className="post__description"><strong>username </strong>
                    description description description description description
                    description description description description description description description
                    description description description description description description description
                    description description description description description description description
                </p>
            </div>
        </div>
    );
};

export default Post;