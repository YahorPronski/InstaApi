import "../../assets/styles/components/main/post.scss"
import "../../assets/styles/common/icons.scss"

const Post = ({user, post}) => {
    const {username} = user;
    const {fileBase64, description, likes} = post;

    return (
        <div className="post">
            <img className="post__image" src={`data:image/jpg;base64,${fileBase64}`} alt="post"/>

            <div className="post__info">
                <div className="post__buttons">
                    <span className="icon heart medium cursor-ptr"></span>
                    <span className="icon comment medium cursor-ptr"></span>
                    <span className="icon paper-plane medium cursor-ptr"></span>
                    <span className="icon bookmark medium cursor-ptr"></span>
                </div>

                <p className="post__likes">{likes} likes</p>

                {description && <p className="post__description">
                    <strong>{username} </strong>
                    {description}
                </p>}
            </div>
        </div>
    );
};

export default Post;