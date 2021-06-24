using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authorization;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.Model.DB.Entities;
using NLog;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private UserRepository userRepository;
        private static Logger logger = LogManager.GetCurrentClassLogger();
        public UsersController(UserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        [HttpGet]
        public async Task<ActionResult<List<User>>> Get()
        {
            try
            {
                return Ok(await userRepository.GetAll());
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("{id:int}")]
        public async Task<ActionResult<User>> Get(int id)
        {
            try
            {
                User user = await userRepository.Get(id);
                if (user == null)
                {
                    logger.Info("User is not found: " + id + ".");
                    return NotFound();
                }

                return Ok(user);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("{username}")]
        public async Task<ActionResult<User>> Get(string username)
        {
            try
            {
                User user = await userRepository.GetByUsername(username);
                if (user == null)
                {
                    logger.Info("User is not found: " + username + ".");
                    return NotFound();
                }

                return Ok(user);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPost]
        public ActionResult<User> Post(User user)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("User ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(userRepository.Create(user));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPut("{id}")]
        public ActionResult<User> Put(long id, User user)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("User ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(userRepository.Update(user));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [AllowAnonymous]
        [HttpPost("authenticate")]
        public async Task<IActionResult> Authenticate([FromBody] LoginRequest model)
        {
            var user = await userRepository.Authenticate(model.Username, model.Password);

            if (user == null)
                return BadRequest(new { message = "Username or password is incorrect" });

            return Ok(user);
        }

        [HttpDelete("{id}")]
        public ActionResult<User> Delete(int id)
        {
            try
            {
                User user = userRepository.Delete(id);
                if (user == null)
                {
                    logger.Info("User is not found: " + id + ".");
                    return NotFound();
                }

                return NoContent();
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }
    }
}
