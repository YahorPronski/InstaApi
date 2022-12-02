import {useState} from "react";
import TextArea from "./fields/TextArea";
import FileUpload from "./fields/FileUpload";
import SubmitButton from "./fields/SubmitButton";
import * as PostService from "../../services/postService";
import "../../assets/styles/components/form/postform.scss"
import {useNavigate} from "react-router-dom";

const PostForm = () => {
    const navigate = useNavigate();
    const [post, setPost] = useState({file: null, description: ''});

    const handleSubmit = () => {
        PostService.savePost(post)
            .then(() => navigate("/profile"));
    };

    const handleFileUpload = (event) => {
        const reader = new FileReader();
        reader.readAsDataURL(event.target.files[0]);
        reader.onload = () => {
            setPost(post => ({
                ...post,
                file: reader.result,
            }));
        };
    };

    const handleTextInput = (event) => {
        setPost(post => ({
            ...post,
            [event.target.name]: event.target.value,
        }));
    };

    return (
        <form className="post-form">
            <FileUpload
                label="Image"
                onChange={handleFileUpload}
                required/>
            <TextArea
                label="Description"
                name="description"
                value={post.description}
                onChange={handleTextInput}/>
            <SubmitButton text="Save" onClick={handleSubmit}/>
        </form>
    );
};

export default PostForm;