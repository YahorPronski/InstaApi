import PostForm from "../../components/form/PostForm";
import Teaser from "../../components/common/Teaser";
import "../../assets/styles/pages/main/newpost.scss"
import logo from '../../assets/images/logo.png';

const NewPost = () => {
    return (
        <div className="new-post-page">
            <Teaser imgSrc={logo} imgAlt="logo">
                Add a new post
            </Teaser>
            <PostForm/>
        </div>
    );
};

export default NewPost;